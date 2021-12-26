package services;

import java.util.List;

import javax.persistence.NoResultException;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.FavoriteConverter;
import actions.views.FavoriteView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Favorite;

/**
 * お気に入りテーブルの操作に関わる処理を行うクラス
 */
public class FavoriteService extends ServiceBase{

    /**
     * 指定した従業員が作成したお気に入りデータを、取得しFavoriteViewのリストで返却する
     * @param employee 従業員
     * @return 一覧画面に表示するデータのリスト
     */
    public List<FavoriteView> getAllMine(EmployeeView employee) {

        List<Favorite> favorites = em.createNamedQuery(JpaConst.Q_FAV_GET_ALL_MINE, Favorite.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getResultList();
        return FavoriteConverter.toViewList(favorites);
    }

    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param fv お気に入りの登録内容
     */
    public int create(FavoriteView fv) {

            createInternal(fv);

            return 0;
    }

    /**
     * 従業員id、レポートidを条件に取得したデータをFavoriteViewのインスタンスで返却する
     * @param  employee 従業員
     * @param report レポートid
     * @param pepper pepper文字列
     * @return 取得データのインスタンス 取得できない場合null
     */
    public FavoriteView findOne(EmployeeView employee, ReportView report) {
        Favorite f = null;
        try {

            //社員番号とハッシュ化済パスワードを条件に未削除の従業員を1件取得する
            f = em.createNamedQuery(JpaConst.Q_FAV_GET_BY_EMP_AND_REP, Favorite.class)
                    .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                    .setParameter(JpaConst.JPQL_PARM_REP, ReportConverter.toModel(report))
                    .getSingleResult();

        } catch (NoResultException ex) {
        }

        return FavoriteConverter.toView(f);

    }

    /**
     * idを条件にお気に入りデータを論理削除する
     * @param id
     */
    public void destroy(Integer id) {


        //idを条件に登録済みのお気に入り情報を取得する
        FavoriteView savedFav = findOne(id);
        if(savedFav != null){
          //論理削除フラグをたてる
            savedFav.setDeleteFlag(JpaConst.FAV_DEL_TRUE);
            //更新処理を行う
            update(savedFav);
        }

    }


    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     */
    public FavoriteView findOne(int id) {
        Favorite f = findOneInternal(id);
        return FavoriteConverter.toView(f);
    }



    private Favorite findOneInternal(int id) {
        Favorite f =  em.find(Favorite.class, id);

        return f;
    }

    /**
     * 日報データを1件登録する
     * @param rv 日報データ
     */
    private void createInternal(FavoriteView fv) {

        em.getTransaction().begin();
        em.persist(FavoriteConverter.toModel(fv));
        em.getTransaction().commit();

    }

    /**
     * お気に入りデータを更新する
     * @param fv 画面から入力されたお気に入りの登録内容
     */
    private void update(FavoriteView fv) {

        em.getTransaction().begin();
        Favorite f = findOneInternal(fv.getF_id());
        FavoriteConverter.copyViewToModel(f, fv);
        em.getTransaction().commit();

    }

}

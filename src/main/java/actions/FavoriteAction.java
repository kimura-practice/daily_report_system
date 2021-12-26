package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.FavoriteView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import services.EmployeeService;
import services.FavoriteService;
import services.ReportService;

/**
 *お気に入りに関する処理を行うActionクラス
 *
 */
public class FavoriteAction extends ActionBase {
    private FavoriteService fs;
    private EmployeeService es;
    private ReportService rs;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException,IOException{
        fs = new FavoriteService();
        es = new EmployeeService();
        rs = new ReportService();

        //メソッドを実行
        invoke();
        fs.close();
        es.close();
        rs.close();
    }

    //登録を行う
    public void create() throws ServletException, IOException{

        //CSRF対策 tokenのチェック
        if(checkToken()) {
            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);
            //レポートidを取得
            ReportView rv = rs.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

            //パラメータの値をもとにお気に入り情報のインスタンスを作成する
            FavoriteView fv = new FavoriteView(
                    null,
                    ev,
                    rv,
                    AttributeConst.F_DEL_FLAG_FALSE.getIntegerValue());

            //お気に入り情報登録
            fs.create(fv);

            //情報登録後の確定した情報をputしている？必要
            putRequestScope(AttributeConst.REPORT, rv); //レポート情報
            putRequestScope(AttributeConst.FAVORITE, fv);//入力されたお気に入り情報
            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

            //詳細画面を表示
            forward(ForwardConst.FW_REP_SHOW);

        }
    }

    //削除
    public void destroy() throws ServletException, IOException{
        //CSRF対策
        if(checkToken()) {

            //EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);
            //idを条件に日報データを取得
            ReportView rv = rs.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
            //idを条件にお気に入りデータを取得
            FavoriteView fv = fs.findOne(toNumber(getRequestParam(AttributeConst.FAV_ID)));
            //System.out.println("aaaaa" + fv.getF_id());

            if(fv.getDeleteFlag() == 0 && fv != null) {
              //idを条件にお気に入りデータを論理削除する
                fs.destroy(toNumber(getRequestParam(AttributeConst.FAV_ID)));

            }else {
                System.out.println("nullまたはflag=1");
            }


            putRequestScope(AttributeConst.REPORT, rv); //レポート情報
            putRequestScope(AttributeConst.FAVORITE, fv);//入力されたお気に入り情報
            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

            //詳細画面にリダイレクト
            forward(ForwardConst.FW_REP_SHOW);
        }
    }

}

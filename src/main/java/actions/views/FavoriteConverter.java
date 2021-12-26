package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Favorite;

/**
 *お気に入りデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class FavoriteConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     */
    public static Favorite toModel(FavoriteView fv) {

        return new Favorite(
                fv.getF_id(),
                EmployeeConverter.toModel(fv.getEmployee()),
                ReportConverter.toModel(fv.getReport()),
                fv.getDeleteFlag() == null
                ? null
                : fv.getDeleteFlag() == AttributeConst.F_DEL_FLAG_TRUE.getIntegerValue()
                        ? JpaConst.FAV_DEL_TRUE
                        : JpaConst.FAV_DEL_FALSE
        );}

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     */
    public static FavoriteView toView(Favorite f) {

        if(f == null) {
            return null;
        }

        return new FavoriteView(
                f.getF_id(),
                EmployeeConverter.toView(f.getEmployee()),
                ReportConverter.toView(f.getReport()),
                f.getDeleteFlag() == null
                ? null
                : f.getDeleteFlag() == JpaConst.FAV_DEL_TRUE
                        ? AttributeConst.F_DEL_FLAG_TRUE.getIntegerValue()
                        : AttributeConst.F_DEL_FLAG_FALSE.getIntegerValue()
                );}

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     */
    public static List<FavoriteView> toViewList(List<Favorite> list){
        List<FavoriteView> fvl = new ArrayList<>();

        for(Favorite f : list) {
            fvl.add(toView(f));
        }
        return fvl;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     */
    public static void copyViewToModel(Favorite f, FavoriteView fv) {
        f.setF_id(fv.getF_id());
        f.setEmployee(EmployeeConverter.toModel(fv.getEmployee()));
        f.setReport(ReportConverter.toModel(fv.getReport()));
        f.setDeleteFlag(fv.getDeleteFlag());

    }

}

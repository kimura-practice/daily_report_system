package actions.views;

import java.util.ArrayList;
import java.util.List;

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
                fv.getId(),
                EmployeeConverter.toModel(fv.getEmployee()),
                ReportConverter.toModel(fv.getReport()));
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     */
    public static FavoriteView toView(Favorite f) {

        if(f == null) {
            return null;
        }

        return new FavoriteView(
                f.getId(),
                EmployeeConverter.toView(f.getEmployee()),
                ReportConverter.toView(f.getReport()));
    }

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
        f.setId(fv.getId());
        f.setEmployee(EmployeeConverter.toModel(fv.getEmployee()));
        f.setReport(ReportConverter.toModel(fv.getReport()));

    }

}

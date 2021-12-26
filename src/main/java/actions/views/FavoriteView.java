package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteView {

    /**
     * id
     */
    private Integer f_id;

    /**
     * お気に入りを登録した従業員
     */
    private EmployeeView employee;

    /**
     * お気に入りに登録したレポート
     */
    private ReportView report;

    /**
     * お気に入りの削除フラグ
     */
    private Integer deleteFlag;
}

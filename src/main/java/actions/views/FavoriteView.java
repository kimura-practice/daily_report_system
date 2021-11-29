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
    private Integer id;

    /**
     * お気に入りを登録した従業員
     */
    private EmployeeView employee;

    /**
     * お気に入りに登録した日報
     */
    private ReportView report;
}

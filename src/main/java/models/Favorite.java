package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *お気に入りデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_FAV)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_FAV_GET_ALL_MINE,
            query = JpaConst.Q_FAV_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_FAV_GET_BY_EMP_AND_REP,
            query = JpaConst.Q_FAV_GET_BY_EMP_AND_REP_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Favorite {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.FAV_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer f_id;

    /**
     *お気に入りを登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FAV_COL_EMP,nullable = false)
    private Employee employee;

    /**
     * お気に入りを登録したレポートのid
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FAV_COL_REP, nullable = false)
    private Report report;

    /**
     * 削除されたお気に入りかどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.FAV_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;



}

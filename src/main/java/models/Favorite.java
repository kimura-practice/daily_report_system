package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private Integer id;

    /**
     *お気に入りを登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FAV_COL_EMP,nullable = false)
    private Employee employee;

    /**
     * お気に入りを登録したレポート
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FAV_COL_REP, nullable = false)
    private Report report;



}

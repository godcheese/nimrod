package com.godcheese.nimrod.common.easyui;

import java.io.Serializable;
import java.util.List;

/**
 * 返回例如：{rows:[{},{}], total:2}
 *
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class Pagination<E> implements Serializable {

    private static final long serialVersionUID = -7480580436678839700L;

    private List<E> rows;
    private long total;
    private boolean inputTotal;

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        if (!inputTotal && getRows() != null) {
            total = getRows().size();
        }
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        inputTotal = true;
    }
}

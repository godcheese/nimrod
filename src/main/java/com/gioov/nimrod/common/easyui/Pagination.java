package com.gioov.nimrod.common.easyui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 返回例如：{rows:[{},{}], total:2}
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class Pagination<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Pagination.class);

        private List<E> rows;
        private int total;
        private boolean inputTotal;

        public List<E> getRows() {
            return rows;
        }

        public void setRows(List<E> rows) {
            this.rows = rows;
        }

        public int getTotal() {
            if(!inputTotal && getRows()!= null) {
                total = getRows().size();
            }
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
            inputTotal = true;
        }

}

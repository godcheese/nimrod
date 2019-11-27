package com.gioov.nimrod.common.exportbyexcel;

import com.gioov.tile.office.ExcelUtil;
import com.gioov.tile.util.ClassUtil;
import com.gioov.tile.web.exception.BaseResponseException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class ExportByExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportByExcelUtil.class);

    private static final String DEFAULT_KEY = "default";

    private static final String EXPORT_HANDLER = "exportHandler";
    private static final String IMPORT_HANDLER = "importHandler";

    private ExportByExcelUtil() {
    }

    /**
     * 实体导出数据成 Excel 表
     * @param httpServletRequest httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param objectList objectList
     * @param clazz clazz
     * @param exportFilename exportFilename
     * @throws BaseResponseException
     */
    public static void exportEntity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, List<? extends Object> objectList, Class<?> clazz, String exportFilename) throws BaseResponseException {

        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            Row row = sheet.createRow(0);
            int fieldIndex = 0;
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ExportByExcel annotation = field.getAnnotation(ExportByExcel.class);
                if (annotation != null) {
                    if(!annotation.exportIgnore()) {
                        String name = annotation.name();
                        if ("".equals(name)) {
                            name = annotation.value();
                        }
                        Cell cell1 = row.createCell(fieldIndex);
                        cell1.setCellValue(name);
                        fieldIndex++;
                    }
                }
            }
            int rowIndex = 1;
            for (Object object : objectList) {
                row = sheet.createRow(rowIndex);
                ExportByExcelUtil.exportFieldValue(row, object);
                rowIndex++;
            }
            ExcelUtil.read2003AndDownloadExportExcel(httpServletRequest, httpServletResponse, workbook, exportFilename);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseResponseException("导出失败");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param row Row
     * @param entity Object
     * @throws IllegalAccessException IllegalAccessException
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws InvocationTargetException InvocationTargetException
     * @throws InstantiationException InstantiationException
     */
    private static void exportFieldValue(Row row, Object entity) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Object value;
        Field[] fields = entity.getClass().getDeclaredFields();
        int fieldIndex = 0;
        for (Field field : fields) {
            ExportByExcel exportByExcel = field.getAnnotation(ExportByExcel.class);
            if (exportByExcel != null) {
                if (!exportByExcel.exportIgnore()) {
                    field.setAccessible(true);
                    Cell cell = row.createCell(fieldIndex);

                    // 导出所有值时自动调用 handler
                    value = ClassUtil.invokeMethod(exportByExcel.handler(), EXPORT_HANDLER, field.get(entity), Object.class);

                    // 获取字段值
                    if (value == null) {
                        value = field.get(entity);
                    }
                    cell.setCellValue(String.valueOf(value));
                    fieldIndex++;
                }
            }
        }
    }

    /**
     * 将实例化的实体遍历赋值
     * @param entity T
     * @param map Map<Integer, Cell>
     * @param <T> <T>
     * @return T T
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws InstantiationException InstantiationException
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     */
    public static <T> T importEntity(T entity, Map<Integer, Cell> map) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Field[] fields = entity.getClass().getDeclaredFields();
        int index = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            ExportByExcel exportByExcel;
            if ((exportByExcel = field.getAnnotation(ExportByExcel.class)) != null) {
                if(!exportByExcel.importIgnore()) {
                    Cell cell = map.get(index);
                    String cellValue = cell.getStringCellValue();
                    // 导入所有值时自动调用 handler
                    Object fieldValue = ClassUtil.invokeMethod(exportByExcel.handler(), IMPORT_HANDLER, cellValue, Object.class);
                    // 给实体字段赋值
                    Class<?> typeClass = field.getType();
                    if (fieldValue != null) {
                        if (typeClass.equals(Long.class)) {
                            field.set(entity, Long.valueOf((String) fieldValue));
                        } else if (typeClass.equals(Integer.class)) {
                            field.set(entity, Integer.valueOf((String) fieldValue));
                        } else if (typeClass.equals(Double.class)) {
                            field.set(entity, Double.valueOf((String) fieldValue));
                        } else if (typeClass.equals(Boolean.class)) {
                            field.set(entity, Boolean.valueOf((String) fieldValue));
                        } else {
                            field.set(entity, fieldValue);
                        }
                    }
                    index++;
                }
            }
        }
        return entity;
    }

}

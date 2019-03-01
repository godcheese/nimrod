package com.gioov.nimrod.system.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.office.ExcelUtil;
import com.gioov.common.util.DateUtil;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.exportbyexcel.ExportByExcelUtil;
import com.gioov.nimrod.system.entity.DictionaryEntity;
import com.gioov.nimrod.system.mapper.DictionaryMapper;
import com.gioov.nimrod.system.service.DictionaryService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Override
    public void addDictionaryToServletContext() {
        /**
         * 是否有效（0=否，1=是，默认=0）
         */
        final int ENABLE_IS = 1;
        final int ENABLE_IS_NOT = 0;

        ServletContext servletContext = webApplicationContext.getServletContext();
        if (servletContext != null) {
            List<DictionaryEntity> dictionaryEntityList = dictionaryMapper.listAll();
            if (dictionaryEntityList != null) {

                // 添加到内存供 servletContext.getAttribute 获取，如：servletContext.getAttribute('WEB.NAME')、${#servletContext.getAttribute('WEB.NAME')}
                for (DictionaryEntity dictionaryEntity : dictionaryEntityList) {
                    if(ENABLE_IS == dictionaryEntity.getEnable()) {
                        servletContext.setAttribute(dictionaryEntity.getKey().toUpperCase() + "." + dictionaryEntity.getValueSlug().toUpperCase(), dictionaryEntity.getValue());
                    }
                }

                // 添加到内存供字典键直接获取，如：WEB
                Map<String, List<DictionaryEntity>> dictionaryEntityMap = new HashMap<>(6);
                for (DictionaryEntity dictionaryEntity : dictionaryEntityList) {
                    if (ENABLE_IS == dictionaryEntity.getEnable()) {
                        String key = dictionaryEntity.getKey().toUpperCase();
                        if (dictionaryEntityMap.containsKey(key)) {
                            List<DictionaryEntity> dictionaryEntityList1 = dictionaryEntityMap.get(key);
                            if (!dictionaryEntityList1.contains(dictionaryEntity)) {
                                dictionaryEntityList1.add(dictionaryEntity);
                                dictionaryEntityMap.put(key, dictionaryEntityList1);
                            }
                        } else {
                            List<DictionaryEntity> dictionaryEntityList2 = new ArrayList<>(1);
                            dictionaryEntityList2.add(dictionaryEntity);
                            dictionaryEntityMap.put(key, dictionaryEntityList2);
                        }
                    }
                }
                for (Map.Entry entry : dictionaryEntityMap.entrySet()) {
                    servletContext.setAttribute((String) entry.getKey(), entry.getValue());
                }

//                Enumeration<String> enumeration = servletContext.getAttributeNames();
//                while (enumeration.hasMoreElements()) {
//                    String key = enumeration.nextElement();
//                    LOGGER.info("{}={}", key,servletContext.getAttribute(key));
//                }

            }
        }
    }

    @Override
    public Object getFromDatabase(String key, String valueSlug) {
        DictionaryEntity dictionaryEntity = dictionaryMapper.getOneByKeyAndValueSlug(key.toUpperCase(), valueSlug.toUpperCase());
        if (dictionaryEntity != null) {
            return dictionaryEntity.getValue();
        }
        return null;
    }

    private Object getValueByKeyAndValueSlug(String key, String valueSlug) {
        ServletContext servletContext = webApplicationContext.getServletContext();
        if (servletContext != null) {
            return servletContext.getAttribute(key + "." + valueSlug);
        } else {
            return null;
        }
    }

    /**
     * 从内存获取
     * @param key       数据字典键
     * @param valueSlug 数据字典值别名
     * @return
     */
    @Override
    public Object get(String key, String valueSlug) {
        return getValueByKeyAndValueSlug(key, valueSlug);
    }

    /**
     * 从内存获取
     * @param key          数据字典键
     * @param valueSlug    数据字典值别名
     * @param defaultValue 数据字典默认值
     * @return
     */
    @Override
    public Object get(String key, String valueSlug, Object defaultValue) {
        Object v = getValueByKeyAndValueSlug(key, valueSlug);
        if (v != null) {
            return v;
        }
        return defaultValue;
    }

    /**
     * 从内存获取
     * @param key
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<DictionaryEntity> get(String key) {
        return (List<DictionaryEntity>) Objects.requireNonNull(webApplicationContext.getServletContext()).getAttribute(key.toUpperCase());
    }

    @Override
    public Map<String, Map<String, Object>> keyValueMap() {
        Map<String, Map<String, Object>> mapMap = new HashMap<>(6);
        List<DictionaryEntity> dictionaryEntityList = dictionaryMapper.listAll();
        if (dictionaryEntityList != null) {
            for (DictionaryEntity dictionaryEntity : dictionaryEntityList) {
                if (mapMap.containsKey(dictionaryEntity.getKey())) {
                    Map<String, Object> valueMap = mapMap.get(dictionaryEntity.getKey());
                    if (!valueMap.containsKey(dictionaryEntity.getValueSlug())) {
                        valueMap.put(dictionaryEntity.getValueSlug(), dictionaryEntity.getValue());
                    }
                    mapMap.put(dictionaryEntity.getKey(), valueMap);
                } else {
                    Map<String, Object> valueMap = new HashMap<>(1);
                    valueMap.put(dictionaryEntity.getValueSlug(), dictionaryEntity.getValue());
                    mapMap.put(dictionaryEntity.getKey(), valueMap);
                }
            }
        }
        return mapMap;
    }

    @Override
    public Pagination.Result<DictionaryEntity> pageAllByDictionaryCategoryId(Long dictionaryCategoryId, Integer page, Integer rows) {
        Pagination.Result<DictionaryEntity> paginationResult = new Pagination().new Result<>();
        List<DictionaryEntity> dictionaryEntityList = dictionaryMapper.pageAllByDictionaryCategoryId(dictionaryCategoryId, new Pageable(page, rows));
        if (dictionaryEntityList != null) {
            paginationResult.setRows(dictionaryEntityList);
        }
        paginationResult.setTotal(dictionaryMapper.countAllByDictionaryCategoryId(dictionaryCategoryId));
        return paginationResult;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DictionaryEntity insertOne(DictionaryEntity dictionaryEntity) {
        Date date = new Date();
        dictionaryEntity.setKey(dictionaryEntity.getKey().toUpperCase());
        dictionaryEntity.setValueSlug(dictionaryEntity.getValueSlug().toUpperCase());
        dictionaryEntity.setGmtModified(date);
        dictionaryEntity.setGmtCreated(date);
        dictionaryMapper.insertOne(dictionaryEntity);
        return dictionaryEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DictionaryEntity updateOne(DictionaryEntity dictionaryEntity) {
        dictionaryEntity.setKey(dictionaryEntity.getKey().toUpperCase());
        dictionaryEntity.setValueSlug(dictionaryEntity.getValueSlug().toUpperCase());
        dictionaryEntity.setGmtModified(new Date());
        dictionaryMapper.updateOne(dictionaryEntity);
        return dictionaryEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) {
        return dictionaryMapper.deleteAll(idList);
    }

    @Override
    public DictionaryEntity getOne(Long id) {
        return dictionaryMapper.getOne(id);
    }

    @Override
    public void exportAllByDictionaryCategoryIdList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, List<Long> idList) throws BaseResponseException {
        List<DictionaryEntity> dictionaryEntityList = new ArrayList<>();
        for (Long id : idList) {
            dictionaryEntityList.addAll(dictionaryMapper.listAllByDictionaryCategoryId(id));
        }
        String filename = "数据字典_" + DateUtil.getNow("yyyyMMddHHmmss") + ".xls";
       ExportByExcelUtil.exportEntity(httpServletRequest, httpServletResponse, dictionaryEntityList, DictionaryEntity.class, filename);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void importAllByDictionaryCategoryId(MultipartFile multipartFile, Long categoryId) throws BaseResponseException {
        try {
            List<Map<Integer, Cell>> list = uploadAndReadExcel(multipartFile);
            list.remove(0);
            for (Map<Integer, Cell> map : list) {
                DictionaryEntity dictionaryEntity = new DictionaryEntity();
                dictionaryEntity.setDictionaryCategoryId(categoryId);
                DictionaryEntity dictionaryEntity1 = ExportByExcelUtil.importEntity(dictionaryEntity, map);
                int effectRows = dictionaryMapper.insertOne(dictionaryEntity1);
                if (effectRows <= 0) {
                    throw new BaseResponseException("导入失败");
                }
            }

        } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new BaseResponseException("导入失败");
        }

    }

    private List<Map<Integer, Cell>> uploadAndReadExcel(MultipartFile multipartFile) throws IOException {
        List<Map<Integer, Cell>> list = new ArrayList<>();
        Workbook workbook = ExcelUtil.getWorkbook(Objects.requireNonNull(multipartFile.getOriginalFilename()), multipartFile.getInputStream());
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowIndex;
            for (rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                int cellIndex;
                Map<Integer, Cell> map = new HashMap<>(1);
                for (cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                    map.put(cellIndex, row.getCell(cellIndex));
                }
                list.add(map);
            }
        }
        return list.isEmpty() ? null : list;
    }

}

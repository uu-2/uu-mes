package cn.yoyo.module.aps.infra.repository.mybatis.dto;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 设备信息表 表定义层。
 *
 * @author three3q
 * @since 2023-09-13
 */
public class TDeviceTableDef extends TableDef {

    /**
     * 设备信息表
     */
    public static final TDeviceTableDef T_DEVICE = new TDeviceTableDef();

    /**
     * 设备ID
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 备注
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 创建者
     */
    public final QueryColumn CREATE_BY = new QueryColumn(this, "create_by");

    /**
     * 更新者
     */
    public final QueryColumn UPDATE_BY = new QueryColumn(this, "update_by");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 设备编码
     */
    public final QueryColumn DEVICE_CODE = new QueryColumn(this, "device_code");

    /**
     * 设备描述
     */
    public final QueryColumn DEVICE_DESC = new QueryColumn(this, "device_desc");

    /**
     * 设备名称
     */
    public final QueryColumn DEVICE_NAME = new QueryColumn(this, "device_name");

    /**
     * 设备类型
     */
    public final QueryColumn DEVICE_TYPE = new QueryColumn(this, "device_type");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, DEVICE_NAME, DEVICE_CODE, DEVICE_TYPE, DEVICE_DESC, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK};

    public TDeviceTableDef() {
        super("yoyo", "t_device");
    }

}

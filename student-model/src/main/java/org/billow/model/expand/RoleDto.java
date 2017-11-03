package org.billow.model.expand;

import org.billow.model.domain.RoleBase;

/**
 * 角色管理model模型<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-02 09:41:50
 */
public class RoleDto extends RoleBase {

    //用于页面显示角色信息是否被选中
    private boolean checked;

    /**
     * 用于页面显示角色信息是否被选中
     *
     * @return
     */
    public boolean getChecked() {
        return checked;
    }

    /**
     * 用于页面显示角色信息是否被选中
     *
     * @param checked
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public RoleDto() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param id
     */
    public RoleDto(Integer id) {
        super(id);
    }

    /**
     * 主键toString 非主键不允许添加
     */
    @Override
    public String toString() {
        return super.toString();
    }
}  
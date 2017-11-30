package org.billow.model.expand;

import org.billow.model.domain.MenuBase;

public class MenuDto extends MenuBase {

    private static final long serialVersionUID = -5259281341286131357L;

    //是否展开
    private boolean open;
    //是否选中
    private boolean checked;
    //菜单名称
    private String name;
    //设置节点的 checkbox / radio 是否禁用
    private boolean chkDisabled = false;

    /**
     * 设置节点的 checkbox / radio 是否禁用
     *
     * @return
     */
    public boolean getChkDisabled() {
        return chkDisabled;
    }

    /**
     * 设置节点的 checkbox / radio 是否禁用
     *
     * @param chkDisabled
     */
    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    /**
     * 是否展开
     *
     * @return
     */
    public boolean getOpen() {
        return open;
    }

    /**
     * 是否展开
     *
     * @param open
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * 是否选中
     *
     * @return
     */
    public boolean getChecked() {
        return checked;
    }

    /**
     * 是否选中
     *
     * @param checked
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * 菜单名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 菜单名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

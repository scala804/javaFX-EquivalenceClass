package com.autoTest.javaFXEquivalenceClass.model;

import java.util.List;

public class ListAllJavaBeans {

    private List<StringTypeBean> listStringTypeBeans;
    private List<InterTypeBean> listInterTypeBeans;
    private List<DateTypeBean> listDateTypeBeans;
    private List<DecimalTypeBean> listDecimalTypeBeans;
    private List<EnumerationTypeBean> listEnumerationTypeBeans;

    public List<StringTypeBean> getListStringTypeBeans() {
        return listStringTypeBeans;
    }

    public void setListStringTypeBeans(List<StringTypeBean> listStringTypeBeans) {
        this.listStringTypeBeans = listStringTypeBeans;
    }

    public List<InterTypeBean> getListInterTypeBeans() {
        return listInterTypeBeans;
    }

    public void setListInterTypeBeans(List<InterTypeBean> listInterTypeBeans) {
        this.listInterTypeBeans = listInterTypeBeans;
    }

    public List<DateTypeBean> getListDateTypeBeans() {
        return listDateTypeBeans;
    }

    public void setListDateTypeBeans(List<DateTypeBean> listDateTypeBeans) {
        this.listDateTypeBeans = listDateTypeBeans;
    }

    public List<DecimalTypeBean> getListDecimalTypeBeans() {
        return listDecimalTypeBeans;
    }

    public void setListDecimalTypeBeans(List<DecimalTypeBean> listDecimalTypeBeans) {
        this.listDecimalTypeBeans = listDecimalTypeBeans;
    }

    public List<EnumerationTypeBean> getListEnumerationTypeBeans() {
        return listEnumerationTypeBeans;
    }

    public void setListEnumerationTypeBeans(List<EnumerationTypeBean> listEnumerationTypeBeans) {
        this.listEnumerationTypeBeans = listEnumerationTypeBeans;
    }

    @Override
    public String toString() {
        return "ListAllJavaBeans{" +
                "listStringTypeBeans=" + listStringTypeBeans +
                ", listInterTypeBeans=" + listInterTypeBeans +
                ", listDateTypeBeans=" + listDateTypeBeans +
                ", listDecimalTypeBeans=" + listDecimalTypeBeans +
                ", listEnumerationTypeBeans=" + listEnumerationTypeBeans +
                '}';
    }
}

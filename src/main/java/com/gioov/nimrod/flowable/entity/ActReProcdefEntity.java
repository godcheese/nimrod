package com.gioov.nimrod.flowable.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */

/**
 * Flowable 流程定义
 */
public class ActReProcdefEntity implements Serializable {

    private static final long serialVersionUID = 5494317516666384245L;

    /**
     * ID_
     */
    public String id_;

    /**
     * REV_
     */
    public Integer rev_;

    /**
     * CATEGORY_
     */
    public String category_;

    /**
     * NAME_
     */
    public String name_;

    /**
     * KEY_
     */
    public String key_;

    /**
     * VERSION_
     */
    public Integer version_;

    /**
     * DEPLOYMENT_ID_
     */
    public String deploymentId_;

    /**
     * RESOURCE_NAME_
     */
    public String resourceName_;

    /**
     * DGRM_RESOURCE_NAME_
     */
    public String dgrmResourceName_;

    /**
     * DESCRIPTION_
     */
    public String description_;

    /**
     * HAS_START_FORM_KEY_
     */
    public Integer hasStartFormKey_;

    /**
     * HAS_GRAPHICAL_NOTATION_
     */
    public String hasGraphicalNotation_;

    /**
     * SUSPENSION_STATE_
     */
    public String suspensionState_;

    /**
     * TENANT_ID_
     */
    public String tenantId_;

    /**
     * ENGINE_VERSION_
     */
    public String engineVersion_;

    /**
     * DERIVED_FROM_
     */
    public String derivedFrom_;

    /**
     * DERIVED_FROM_ROOT_
     */
    public String derivedFRromRoot_;

    /**
     * DERIVED_VERSION_
     */
    public Integer derivedVersion_;


    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public Integer getRev_() {
        return rev_;
    }

    public void setRev_(Integer rev_) {
        this.rev_ = rev_;
    }

    public String getCategory_() {
        return category_;
    }

    public void setCategory_(String category_) {
        this.category_ = category_;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getKey_() {
        return key_;
    }

    public void setKey_(String key_) {
        this.key_ = key_;
    }

    public Integer getVersion_() {
        return version_;
    }

    public void setVersion_(Integer version_) {
        this.version_ = version_;
    }

    public String getDeploymentId_() {
        return deploymentId_;
    }

    public void setDeploymentId_(String deploymentId_) {
        this.deploymentId_ = deploymentId_;
    }

    public String getResourceName_() {
        return resourceName_;
    }

    public void setResourceName_(String resourceName_) {
        this.resourceName_ = resourceName_;
    }

    public String getDgrmResourceName_() {
        return dgrmResourceName_;
    }

    public void setDgrmResourceName_(String dgrmResourceName_) {
        this.dgrmResourceName_ = dgrmResourceName_;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public Integer getHasStartFormKey_() {
        return hasStartFormKey_;
    }

    public void setHasStartFormKey_(Integer hasStartFormKey_) {
        this.hasStartFormKey_ = hasStartFormKey_;
    }

    public String getHasGraphicalNotation_() {
        return hasGraphicalNotation_;
    }

    public void setHasGraphicalNotation_(String hasGraphicalNotation_) {
        this.hasGraphicalNotation_ = hasGraphicalNotation_;
    }

    public String getSuspensionState_() {
        return suspensionState_;
    }

    public void setSuspensionState_(String suspensionState_) {
        this.suspensionState_ = suspensionState_;
    }

    public String getTenantId_() {
        return tenantId_;
    }

    public void setTenantId_(String tenantId_) {
        this.tenantId_ = tenantId_;
    }

    public String getEngineVersion_() {
        return engineVersion_;
    }

    public void setEngineVersion_(String engineVersion_) {
        this.engineVersion_ = engineVersion_;
    }

    public String getDerivedFrom_() {
        return derivedFrom_;
    }

    public void setDerivedFrom_(String derivedFrom_) {
        this.derivedFrom_ = derivedFrom_;
    }

    public String getDerivedFRromRoot_() {
        return derivedFRromRoot_;
    }

    public void setDerivedFRromRoot_(String derivedFRromRoot_) {
        this.derivedFRromRoot_ = derivedFRromRoot_;
    }

    public Integer getDerivedVersion_() {
        return derivedVersion_;
    }

    public void setDerivedVersion_(Integer derivedVersion_) {
        this.derivedVersion_ = derivedVersion_;
    }
}

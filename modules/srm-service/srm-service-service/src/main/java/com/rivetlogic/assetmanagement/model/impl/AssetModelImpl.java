/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.rivetlogic.assetmanagement.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.rivetlogic.assetmanagement.model.Asset;
import com.rivetlogic.assetmanagement.model.AssetModel;
import com.rivetlogic.assetmanagement.model.AssetPhotoBlobModel;
import com.rivetlogic.assetmanagement.service.AssetLocalServiceUtil;

import java.io.Serializable;

import java.sql.Blob;
import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Asset service. Represents a row in the &quot;rivetlogic_Asset&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link AssetModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetImpl
 * @see Asset
 * @see AssetModel
 * @generated
 */
@ProviderType
public class AssetModelImpl extends BaseModelImpl<Asset> implements AssetModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset model instance should use the {@link Asset} interface instead.
	 */
	public static final String TABLE_NAME = "rivetlogic_Asset";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "assetId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "location", Types.BIGINT },
			{ "active_", Types.BOOLEAN },
			{ "category", Types.BIGINT },
			{ "status", Types.VARCHAR },
			{ "mimeType", Types.VARCHAR },
			{ "photo", Types.BLOB },
			{ "currentUserId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assetId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("location", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("category", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("status", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("mimeType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("photo", Types.BLOB);
		TABLE_COLUMNS_MAP.put("currentUserId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table rivetlogic_Asset (uuid_ VARCHAR(75) null,assetId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,description VARCHAR(75) null,location LONG,active_ BOOLEAN,category LONG,status VARCHAR(75) null,mimeType VARCHAR(75) null,photo BLOB,currentUserId LONG)";
	public static final String TABLE_SQL_DROP = "drop table rivetlogic_Asset";
	public static final String ORDER_BY_JPQL = " ORDER BY asset.assetId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY rivetlogic_Asset.assetId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.rivetlogic.assetmanagement.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.rivetlogic.assetmanagement.model.Asset"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.rivetlogic.assetmanagement.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.rivetlogic.assetmanagement.model.Asset"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.rivetlogic.assetmanagement.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.rivetlogic.assetmanagement.model.Asset"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long USERID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long ASSETID_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.rivetlogic.assetmanagement.service.util.ServiceProps.get(
				"lock.expiration.time.com.rivetlogic.assetmanagement.model.Asset"));

	public AssetModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _assetId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAssetId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Asset.class;
	}

	@Override
	public String getModelClassName() {
		return Asset.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("assetId", getAssetId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("location", getLocation());
		attributes.put("active", getActive());
		attributes.put("category", getCategory());
		attributes.put("status", getStatus());
		attributes.put("mimeType", getMimeType());
		attributes.put("photo", getPhoto());
		attributes.put("currentUserId", getCurrentUserId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long assetId = (Long)attributes.get("assetId");

		if (assetId != null) {
			setAssetId(assetId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long location = (Long)attributes.get("location");

		if (location != null) {
			setLocation(location);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Long category = (Long)attributes.get("category");

		if (category != null) {
			setCategory(category);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String mimeType = (String)attributes.get("mimeType");

		if (mimeType != null) {
			setMimeType(mimeType);
		}

		Blob photo = (Blob)attributes.get("photo");

		if (photo != null) {
			setPhoto(photo);
		}

		Long currentUserId = (Long)attributes.get("currentUserId");

		if (currentUserId != null) {
			setCurrentUserId(currentUserId);
		}
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getAssetId() {
		return _assetId;
	}

	@Override
	public void setAssetId(long assetId) {
		_columnBitmask = -1L;

		_assetId = assetId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public long getLocation() {
		return _location;
	}

	@Override
	public void setLocation(long location) {
		_location = location;
	}

	@Override
	public boolean getActive() {
		return _active;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_active = active;
	}

	@Override
	public long getCategory() {
		return _category;
	}

	@Override
	public void setCategory(long category) {
		_category = category;
	}

	@Override
	public String getStatus() {
		if (_status == null) {
			return StringPool.BLANK;
		}
		else {
			return _status;
		}
	}

	@Override
	public void setStatus(String status) {
		_status = status;
	}

	@Override
	public String getMimeType() {
		if (_mimeType == null) {
			return StringPool.BLANK;
		}
		else {
			return _mimeType;
		}
	}

	@Override
	public void setMimeType(String mimeType) {
		_mimeType = mimeType;
	}

	@Override
	public Blob getPhoto() {
		if (_photoBlobModel == null) {
			try {
				_photoBlobModel = AssetLocalServiceUtil.getPhotoBlobModel(getPrimaryKey());
			}
			catch (Exception e) {
			}
		}

		Blob blob = null;

		if (_photoBlobModel != null) {
			blob = _photoBlobModel.getPhotoBlob();
		}

		return blob;
	}

	@Override
	public void setPhoto(Blob photo) {
		if (_photoBlobModel == null) {
			_photoBlobModel = new AssetPhotoBlobModel(getPrimaryKey(), photo);
		}
		else {
			_photoBlobModel.setPhotoBlob(photo);
		}
	}

	@Override
	public Long getCurrentUserId() {
		return _currentUserId;
	}

	@Override
	public void setCurrentUserId(Long currentUserId) {
		_currentUserId = currentUserId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Asset.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Asset.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Asset toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Asset)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AssetImpl assetImpl = new AssetImpl();

		assetImpl.setUuid(getUuid());
		assetImpl.setAssetId(getAssetId());
		assetImpl.setGroupId(getGroupId());
		assetImpl.setCompanyId(getCompanyId());
		assetImpl.setUserId(getUserId());
		assetImpl.setUserName(getUserName());
		assetImpl.setCreateDate(getCreateDate());
		assetImpl.setModifiedDate(getModifiedDate());
		assetImpl.setName(getName());
		assetImpl.setDescription(getDescription());
		assetImpl.setLocation(getLocation());
		assetImpl.setActive(getActive());
		assetImpl.setCategory(getCategory());
		assetImpl.setStatus(getStatus());
		assetImpl.setMimeType(getMimeType());
		assetImpl.setCurrentUserId(getCurrentUserId());

		assetImpl.resetOriginalValues();

		return assetImpl;
	}

	@Override
	public int compareTo(Asset asset) {
		int value = 0;

		if (getAssetId() < asset.getAssetId()) {
			value = -1;
		}
		else if (getAssetId() > asset.getAssetId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Asset)) {
			return false;
		}

		Asset asset = (Asset)obj;

		long primaryKey = asset.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		AssetModelImpl assetModelImpl = this;

		assetModelImpl._originalUuid = assetModelImpl._uuid;

		assetModelImpl._originalGroupId = assetModelImpl._groupId;

		assetModelImpl._setOriginalGroupId = false;

		assetModelImpl._originalCompanyId = assetModelImpl._companyId;

		assetModelImpl._setOriginalCompanyId = false;

		assetModelImpl._originalUserId = assetModelImpl._userId;

		assetModelImpl._setOriginalUserId = false;

		assetModelImpl._setModifiedDate = false;

		assetModelImpl._photoBlobModel = null;

		assetModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Asset> toCacheModel() {
		AssetCacheModel assetCacheModel = new AssetCacheModel();

		assetCacheModel.uuid = getUuid();

		String uuid = assetCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			assetCacheModel.uuid = null;
		}

		assetCacheModel.assetId = getAssetId();

		assetCacheModel.groupId = getGroupId();

		assetCacheModel.companyId = getCompanyId();

		assetCacheModel.userId = getUserId();

		assetCacheModel.userName = getUserName();

		String userName = assetCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			assetCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			assetCacheModel.createDate = createDate.getTime();
		}
		else {
			assetCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			assetCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			assetCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		assetCacheModel.name = getName();

		String name = assetCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			assetCacheModel.name = null;
		}

		assetCacheModel.description = getDescription();

		String description = assetCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			assetCacheModel.description = null;
		}

		assetCacheModel.location = getLocation();

		assetCacheModel.active = getActive();

		assetCacheModel.category = getCategory();

		assetCacheModel.status = getStatus();

		String status = assetCacheModel.status;

		if ((status != null) && (status.length() == 0)) {
			assetCacheModel.status = null;
		}

		assetCacheModel.mimeType = getMimeType();

		String mimeType = assetCacheModel.mimeType;

		if ((mimeType != null) && (mimeType.length() == 0)) {
			assetCacheModel.mimeType = null;
		}

		assetCacheModel.currentUserId = getCurrentUserId();

		return assetCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", assetId=");
		sb.append(getAssetId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", location=");
		sb.append(getLocation());
		sb.append(", active=");
		sb.append(getActive());
		sb.append(", category=");
		sb.append(getCategory());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", mimeType=");
		sb.append(getMimeType());
		sb.append(", currentUserId=");
		sb.append(getCurrentUserId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.rivetlogic.assetmanagement.model.Asset");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetId</column-name><column-value><![CDATA[");
		sb.append(getAssetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>location</column-name><column-value><![CDATA[");
		sb.append(getLocation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>category</column-name><column-value><![CDATA[");
		sb.append(getCategory());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mimeType</column-name><column-value><![CDATA[");
		sb.append(getMimeType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentUserId</column-name><column-value><![CDATA[");
		sb.append(getCurrentUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Asset.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Asset.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _assetId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _description;
	private long _location;
	private boolean _active;
	private long _category;
	private String _status;
	private String _mimeType;
	private AssetPhotoBlobModel _photoBlobModel;
	private Long _currentUserId;
	private long _columnBitmask;
	private Asset _escapedModel;
}
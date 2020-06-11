package com.elementary.spring.mvc.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="notifications")
public class Notification {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
    private int id;

	private String live_mode;

	private String descripcion;

	private String type;

	private Date date_created;

	private Integer application_id;

	private Integer user_id;

	private Integer version;

	private String api_version;

	private String action;

	private String data_id;


	public Notification(int id, String live_mode, String descripcion, String type, Date date_created, Integer application_id, Integer user_id, Integer version, String api_version, String action, String data_id) {
		this.id = id;
		this.live_mode = live_mode;
		this.descripcion = descripcion;
		this.type = type;
		this.date_created = date_created;
		this.application_id = application_id;
		this.user_id = user_id;
		this.version = version;
		this.api_version = api_version;
		this.action = action;
		this.data_id = data_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLive_mode() {
		return live_mode;
	}

	public void setLive_mode(String live_mode) {
		this.live_mode = live_mode;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public Integer getApplication_id() {
		return application_id;
	}

	public void setApplication_id(Integer application_id) {
		this.application_id = application_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getApi_version() {
		return api_version;
	}

	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getData_id() {
		return data_id;
	}

	public void setData_id(String data_id) {
		this.data_id = data_id;
	}

	@Override
	public String toString() {
		return "Payment{" +
				"id=" + id +
				", live_mode='" + live_mode + '\'' +
				", descripcion='" + descripcion + '\'' +
				", type='" + type + '\'' +
				", date_created=" + date_created +
				", application_id=" + application_id +
				", user_id=" + user_id +
				", version=" + version +
				", api_version='" + api_version + '\'' +
				", action='" + action + '\'' +
				", data_id='" + data_id + '\'' +
				'}';
	}
}

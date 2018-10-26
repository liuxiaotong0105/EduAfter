package cc.mrbird.system.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_movie")
public class Movie implements Serializable {



	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "movieId")
	private Integer movieId;

	@Column(name = "movieName")
	private String movieName;

	@Column(name = "movieUrl")
	private String movieUrl;

	@Column(name = "movieStatus")
	private Integer movieStatus;

	@Column(name = "movieType")
	private String movieType;

	@Column(name = "moviePicther")
	private String moviePicther;


	@Column(name = "movieInfo")
	private String movieInfo;

	@Column(name = "moviePrice")
	private String moviePrice;

	@Column(name = "movieTeacher")
	private String movieTeacher;

	@Column(name = "teacherid")
	private Integer teacherid;

	@Column(name = "freeStatus")
	private Integer freeStatus;

	@Column(name = "movieClass")
	private String movieClass;

	@Column(name = "teacherName")
	private String teacherName;

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}

	public Integer getMovieStatus() {
		return movieStatus;
	}

	public void setMovieStatus(Integer movieStatus) {
		this.movieStatus = movieStatus;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public String getMoviePicther() {
		return moviePicther;
	}

	public void setMoviePicther(String moviePicther) {
		this.moviePicther = moviePicther;
	}

	public String getMovieInfo() {
		return movieInfo;
	}

	public void setMovieInfo(String movieInfo) {
		this.movieInfo = movieInfo;
	}

	public String getMoviePrice() {
		return moviePrice;
	}

	public void setMoviePrice(String moviePrice) {
		this.moviePrice = moviePrice;
	}

	public String getMovieTeacher() {
		return movieTeacher;
	}

	public void setMovieTeacher(String movieTeacher) {
		this.movieTeacher = movieTeacher;
	}

	public Integer getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	public Integer getFreeStatus() {
		return freeStatus;
	}

	public void setFreeStatus(Integer freeStatus) {
		this.freeStatus = freeStatus;
	}

	public String getMovieClass() {
		return movieClass;
	}

	public void setMovieClass(String movieClass) {
		this.movieClass = movieClass;
	}
}
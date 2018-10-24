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
	private String movieId;

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

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public Integer getMovieStatus() {
		return movieStatus;
	}

	public void setMovieStatus(Integer movieStatus) {
		this.movieStatus = movieStatus;
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
}
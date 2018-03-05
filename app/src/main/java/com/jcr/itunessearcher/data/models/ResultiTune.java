package com.jcr.itunessearcher.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "iTunes", indices = {@Index(value = {"trackId"}, unique = true)})
public class ResultiTune {

	@SerializedName("artworkUrl100")
	public String artworkUrl100;

	@SerializedName("trackTimeMillis")
	public int trackTimeMillis;

	@SerializedName("country")
	public String country;

	@SerializedName("previewUrl")
	public String previewUrl;

	@SerializedName("artistId")
	public int artistId;

	@SerializedName("trackName")
	public String trackName;

	@SerializedName("collectionName")
	public String collectionName;

	@SerializedName("artistViewUrl")
	public String artistViewUrl;

	@SerializedName("discNumber")
	public int discNumber;

	@SerializedName("trackCount")
	public int trackCount;

	@SerializedName("artworkUrl30")
	public String artworkUrl30;

	@SerializedName("wrapperType")
	public String wrapperType;

	@SerializedName("currency")
	public String currency;

	@SerializedName("collectionId")
	public int collectionId;

	@SerializedName("isStreamable")
	public boolean isStreamable;

	@SerializedName("trackExplicitness")
	public String trackExplicitness;

	@SerializedName("collectionViewUrl")
	public String collectionViewUrl;

	@SerializedName("trackNumber")
	public int trackNumber;

	@SerializedName("releaseDate")
	public String releaseDate;

	@SerializedName("kind")
	public String kind;

	@PrimaryKey
	@SerializedName("trackId")
	public int trackId;

	@SerializedName("collectionPrice")
	public double collectionPrice;

	@SerializedName("discCount")
	public int discCount;

	@SerializedName("primaryGenreName")
	public String primaryGenreName;

	@SerializedName("trackPrice")
	public double trackPrice;

	@SerializedName("collectionExplicitness")
	public String collectionExplicitness;

	@SerializedName("trackViewUrl")
	public String trackViewUrl;

	@SerializedName("artworkUrl60")
	public String artworkUrl60;

	@SerializedName("trackCensoredName")
	public String trackCensoredName;

	@SerializedName("artistName")
	public String artistName;

	@SerializedName("collectionCensoredName")
	public String collectionCensoredName;

	public ResultiTune(String artworkUrl100, int trackTimeMillis, String country, String previewUrl, int artistId, String trackName, String collectionName, String artistViewUrl, int discNumber, int trackCount, String artworkUrl30, String wrapperType, String currency, int collectionId, boolean isStreamable, String trackExplicitness, String collectionViewUrl, int trackNumber, String releaseDate, String kind, int trackId, double collectionPrice, int discCount, String primaryGenreName, double trackPrice, String collectionExplicitness, String trackViewUrl, String artworkUrl60, String trackCensoredName, String artistName, String collectionCensoredName) {
		this.artworkUrl100 = artworkUrl100;
		this.trackTimeMillis = trackTimeMillis;
		this.country = country;
		this.previewUrl = previewUrl;
		this.artistId = artistId;
		this.trackName = trackName;
		this.collectionName = collectionName;
		this.artistViewUrl = artistViewUrl;
		this.discNumber = discNumber;
		this.trackCount = trackCount;
		this.artworkUrl30 = artworkUrl30;
		this.wrapperType = wrapperType;
		this.currency = currency;
		this.collectionId = collectionId;
		this.isStreamable = isStreamable;
		this.trackExplicitness = trackExplicitness;
		this.collectionViewUrl = collectionViewUrl;
		this.trackNumber = trackNumber;
		this.releaseDate = releaseDate;
		this.kind = kind;
		this.trackId = trackId;
		this.collectionPrice = collectionPrice;
		this.discCount = discCount;
		this.primaryGenreName = primaryGenreName;
		this.trackPrice = trackPrice;
		this.collectionExplicitness = collectionExplicitness;
		this.trackViewUrl = trackViewUrl;
		this.artworkUrl60 = artworkUrl60;
		this.trackCensoredName = trackCensoredName;
		this.artistName = artistName;
		this.collectionCensoredName = collectionCensoredName;
	}

	@Override
 	public String toString(){
		return 
			"ResultiTune{" +
			"artworkUrl100 = '" + artworkUrl100 + '\'' + 
			",trackTimeMillis = '" + trackTimeMillis + '\'' + 
			",country = '" + country + '\'' + 
			",previewUrl = '" + previewUrl + '\'' + 
			",artistId = '" + artistId + '\'' + 
			",trackName = '" + trackName + '\'' + 
			",collectionName = '" + collectionName + '\'' + 
			",artistViewUrl = '" + artistViewUrl + '\'' + 
			",discNumber = '" + discNumber + '\'' + 
			",trackCount = '" + trackCount + '\'' + 
			",artworkUrl30 = '" + artworkUrl30 + '\'' + 
			",wrapperType = '" + wrapperType + '\'' + 
			",currency = '" + currency + '\'' + 
			",collectionId = '" + collectionId + '\'' + 
			",isStreamable = '" + isStreamable + '\'' + 
			",trackExplicitness = '" + trackExplicitness + '\'' + 
			",collectionViewUrl = '" + collectionViewUrl + '\'' + 
			",trackNumber = '" + trackNumber + '\'' + 
			",releaseDate = '" + releaseDate + '\'' + 
			",kind = '" + kind + '\'' + 
			",trackId = '" + trackId + '\'' + 
			",collectionPrice = '" + collectionPrice + '\'' + 
			",discCount = '" + discCount + '\'' + 
			",primaryGenreName = '" + primaryGenreName + '\'' + 
			",trackPrice = '" + trackPrice + '\'' + 
			",collectionExplicitness = '" + collectionExplicitness + '\'' + 
			",trackViewUrl = '" + trackViewUrl + '\'' + 
			",artworkUrl60 = '" + artworkUrl60 + '\'' + 
			",trackCensoredName = '" + trackCensoredName + '\'' + 
			",artistName = '" + artistName + '\'' + 
			",collectionCensoredName = '" + collectionCensoredName + '\'' + 
			"}";
		}
}
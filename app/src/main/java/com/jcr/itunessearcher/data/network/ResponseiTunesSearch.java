package com.jcr.itunessearcher.data.network;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.jcr.itunessearcher.data.models.ResultiTune;

import java.util.ArrayList;
import java.util.List;

public class ResponseiTunesSearch {

	@SerializedName("resultCount")
	private int resultCount;

	@SerializedName("results")
	private List<ResultiTune> results;

	public void setResultCount(int resultCount){
		this.resultCount = resultCount;
	}

	public int getResultCount(){
		return resultCount;
	}

	public void setResults(List<ResultiTune> results){
		this.results = results;
	}

	public List<ResultiTune> getResults(){
		return results;
	}

	public ResponseiTunesSearch(int resultCount, List<ResultiTune> results) {
		this.resultCount = resultCount;
		this.results = results;
	}

	@Override
 	public String toString(){
		return 
			"ResponseiTunesSearch{" +
			"resultCount = '" + resultCount + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}

	@NonNull
	public List<Integer> getRepoIds() {
		List<Integer> trackIds = new ArrayList<>();
		for (ResultiTune resultiTune : results) {
			trackIds.add(resultiTune.trackId);
		}
		return trackIds;
	}
}
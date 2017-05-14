package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.DB;

import com.orm.SugarRecord;

/**
 * Created by nerdywoffy on 5/14/17.
 */

public class TVShows extends SugarRecord{

    public TVShows(){

    }
    public TVShows(String tvId, String image, String title, String originalTitle, String overview, String status, String createdBy, String productionCompany, String firstAiringDate, String lastAiringDate, String amountOfSeasons, String voteAverage, String year) {
        this.tvId = tvId;
        this.image = image;
        this.title = title;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.status = status;
        this.createdBy = createdBy;
        this.productionCompany = productionCompany;
        this.firstAiringDate = firstAiringDate;
        this.lastAiringDate = lastAiringDate;
        this.amountOfSeasons = amountOfSeasons;
        this.voteAverage = voteAverage;
        this.year = year;
    }

    public String getTvId() {
        return tvId;
    }

    public void setTvId(String tvId) {
        this.tvId = tvId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public String getFirstAiringDate() {
        return firstAiringDate;
    }

    public void setFirstAiringDate(String firstAiringDate) {
        this.firstAiringDate = firstAiringDate;
    }

    public String getLastAiringDate() {
        return lastAiringDate;
    }

    public void setLastAiringDate(String lastAiringDate) {
        this.lastAiringDate = lastAiringDate;
    }

    public String getAmountOfSeasons() {
        return amountOfSeasons;
    }

    public void setAmountOfSeasons(String amountOfSeasons) {
        this.amountOfSeasons = amountOfSeasons;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    String tvId;
    String image;
    String title;
    String originalTitle;
    String overview;
    String status;
    String createdBy;
    String productionCompany;
    String firstAiringDate;
    String lastAiringDate;
    String amountOfSeasons;
    String voteAverage;
    String year;
}

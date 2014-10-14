package com.example.bioblitz;

import android.os.Parcel;
import android.os.Parcelable;

public class Record implements Parcelable{

	private String imagePath;
	private String commonName;
	private String scientificName;
	private String dateRecorded;
	private String recorder;
	
	public Record(String imagePath, String commonName, String scientificName,
			String dateRecorded, String recorder) {
		super();
		this.imagePath = imagePath;
		this.commonName = commonName;
		this.scientificName = scientificName;
		this.dateRecorded = dateRecorded;
		this.recorder = recorder;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	public String getDateRecorded() {
		return dateRecorded;
	}
	public void setDateRecorded(String dateRecorded) {
		this.dateRecorded = dateRecorded;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	
	// Parcelling part
    public Record(Parcel in){
        String[] data = new String[5];

        in.readStringArray(data);
        this.imagePath = data[0];
        this.commonName = data[1];
        this.scientificName = data[2];
        this.dateRecorded = data[3];
        this.recorder = data[4];
        
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.imagePath,
                                            this.commonName,
                                            this.scientificName,
                                            this.dateRecorded,
                                            this.recorder});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Record createFromParcel(Parcel in) {
            return new Record(in); 
        }

        public Record[] newArray(int size) {
            return new Record[size];
        }
    };
}

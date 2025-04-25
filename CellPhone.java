public class CellPhone {

    ITelephoneMic mic;
    ITelephoneSpeaker speaker;

    public CellPhone(){
        this.mic = null;
        this.speaker = null;
    }

    public CellPhone(ITelephoneMic mic, ITelephoneSpeaker speaker){
        setMic(mic);
        setSpeaker(speaker);
    }

    public ITelephoneMic getMic() {
        return mic;
    }

    public ITelephoneSpeaker getSpeaker() {
        return speaker;
    }

    public void setMic(ITelephoneMic mic) {
        this.mic = mic;
    }

    public void setSpeaker(ITelephoneSpeaker speaker) {
        this.speaker = speaker;
    }
}

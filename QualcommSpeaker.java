public class QualcommSpeaker extends BaseSpeaker{

    @Override
    public boolean unMute() {
        if(volume > 80){
            isMuted = true;
            return false;
        }
        return true;
    }
}

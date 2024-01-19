package rhythm_game;

public class Track {
    private String startImage;
    private String gameImage;
    private String startMusic;
    private String gameMusic;

    public Track(String startImage, String gameImage, String startMusic, String gameMusic){
        this.startImage = startImage;
        this.gameImage = gameImage;
        this.startMusic = startMusic;
        this.gameMusic = gameMusic;
    }

    public String getStartImage() {
        return startImage;
    }

    public void setStartImage(String startImage) {
        this.startImage = startImage;
    }

    public String getGameImage() {
        return gameImage;
    }

    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }

    public String getStartMusic() {
        return startMusic;
    }

    public void setStartMusic(String startMusic) {
        this.startMusic = startMusic;
    }

    public String getGameMusic() {
        return gameMusic;
    }

    public void setGameMusic(String gameMusic) {
        this.gameMusic = gameMusic;
    }
}

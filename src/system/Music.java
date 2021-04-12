package system;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import javazoom.jl.player.Player;
//
public class Music extends Thread {
}

//	private Player player;
//	private boolean isLoop;
//	private File file;
//	private FileInputStream fis;
//	private BufferedInputStream bis;
//
//	public Music(String name, boolean isLoop) {
//		try {
//			this.isLoop = isLoop;
//			this.file = new File(Music.class.getResource("..\\music\\" + name).toURI());
//			this.fis = new FileInputStream(this.file);
//			this.bis = new BufferedInputStream(this.fis);
//			this.player = new Player(this.bis);
//		} catch (Exception var4) {
//			System.out.println("constructor   " + var4.getMessage());
//		}
//	} // Music 생성자
//
//	public int getTime() {
//		if (player == null) {
//			return 0;
//		}
//		return player.getPosition();
//	}
//	
//    public void close(){
//        isLoop = false;
//        player.close();
//        this.interrupt();
//    }
//    
//    public void startMusic(){
//        try{
//            do{
//                player.play();
//                fis = new FileInputStream(file);
//                bis = new BufferedInputStream(fis);
//                player = new Player(bis);
//            }while(isLoop);
//        }catch (Exception e){
//            e.getMessage();
//        }
//    } // start 메소드
//
//    @Override
//	public void run() {
//        try{
//            do{
//                player.play();
//                fis = new FileInputStream(file);
//                bis = new BufferedInputStream(fis);
//                player = new Player(bis);
//            }while(isLoop);
//        }catch (Exception e){
//            e.getMessage();
//        }
//	} // run 메소드
//	
//} // Music 클래스

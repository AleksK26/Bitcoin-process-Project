import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> blocks;
    private String latestBlockHash;

    public Blockchain() {
        blocks = new ArrayList<>();
        latestBlockHash = "";
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public Block getBlock(int index) {
        return blocks.get(index);
    }

    public int getSize() {
        return blocks.size();
    }

    public String getLatestBlockHash() {
        return latestBlockHash;
    }

    public void setLatestBlockHash(String latestBlockHash) {
        this.latestBlockHash = latestBlockHash;
    }

    public void printBlockchain() {
        for (Block block : blocks) {
            System.out.println("Block Hash: " + block.getHash());
            System.out.println("Previous Block Hash: " + block.getPreviousHash());
            System.out.println("Transaction: " + block.getTransaction());
            System.out.println();
        }
    }
}

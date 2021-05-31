import eu.miopowered.repository.Key;
import eu.miopowered.repository.Repository;
import eu.miopowered.repository.impl.GsonRepository;

import java.nio.file.Path;
import java.util.UUID;

public class TestMain {

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        Path directory = Path.of("testing");
        directory.toFile().mkdir();
        Repository<Player> playerRepository = GsonRepository.of(directory, Player.class);
        System.out.println("Trying to data that dont exist: " + playerRepository.delete(Key.wrap(uuid)));
        System.out.println("Data: " + playerRepository.all());
        Player player = new Player(uuid, 100, false);
        System.out.println("Inserting data: " + playerRepository.insert(player));
        System.out.println("Data: " + playerRepository.all());
        player.admin = true;
        System.out.println("Updating data: " + playerRepository.update(player));
        System.out.println("Deleting data: " + playerRepository.delete(Key.wrap(uuid)));
    }

    public static class Player implements Key {

        private UUID uuid;
        private int coins;
        private boolean admin;

        public Player(UUID uuid, int coins, boolean admin) {
            this.uuid = uuid;
            this.coins = coins;
            this.admin = admin;
        }

        @Override
        public String getKey() {
            return this.uuid.toString();
        }
    }
}

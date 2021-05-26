package me.jazzyjake.player;

public enum PlayerColor {
    RED {
        @Override
        public Class getPlayerClass() {
            return RedPlayer.class;
        }
    }, BLUE {
        @Override
        public Class getPlayerClass() {
            return BluePlayer.class;
        }
    };

    public abstract Class getPlayerClass();
}
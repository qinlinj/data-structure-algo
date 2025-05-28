package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._843_fallout4_game;

import java.util.*;

public class _843_c_DPStateDesign {
    /**
     * DP State representation for the Freedom Trail problem
     */
    static class FreedomTrailState {
        int ringPosition;    // Current position of pointer on ring
        int keyIndex;        // Current index in key string being processed

        public FreedomTrailState(int ringPos, int keyIdx) {
            this.ringPosition = ringPos;
            this.keyIndex = keyIdx;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof FreedomTrailState)) return false;
            FreedomTrailState other = (FreedomTrailState) obj;
            return this.ringPosition == other.ringPosition && this.keyIndex == other.keyIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ringPosition, keyIndex);
        }

        @Override
        public String toString() {
            return String.format("State(ring=%d, key=%d)", ringPosition, keyIndex);
        }
    }
}

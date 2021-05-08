/**
 * NP uses a pretty standard X/Y coordinate system to pinpoint locations,
 * including stars, fleets, fleet orders etc.
 * All of them use the same X/Y system.
 * However, translating this system to what we see on the map is not that straightforward
 * as the map shows light years and they are not the same.
 * <p>
 * A value of 1 in the X/Y system is equal to 8 light years (e.g. 0,0 and 1,0 are 8 light years apart).
 * Ships in the game move at 1 LY/tick at warp speed and 0.33 LY/tick at normal speed.
 */
package io.github.eutro.neptunespride.types;
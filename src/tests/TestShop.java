package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import sgf.controller.game.PlayerController;
import sgf.controller.game.PlayerControllerImpl;
import sgf.controller.shop.ShopController;
import sgf.controller.shop.ShopControllerImpl;
import sgf.helpers.TurretsLoader;
import sgf.managers.GameManager;
import sgf.managers.GameManagerImpl;
import sgf.model.game.Player;
import sgf.model.game.PlayerImpl;
import sgf.model.shop.Shop;
import sgf.model.shop.ShopImpl;
import sgf.model.turret.Turret;
import sgf.model.turret.TurretImpl;
import sgf.view.game.PlayerView;
import sgf.view.game.PlayerViewImpl;
import sgf.view.shop.ShopView;
import sgf.view.shop.ShopViewImpl;

/**
 * Class for tests on the shop.
 */
class TestShop {

    private static final int INIT_MONEY = 300;
    private final Shop s;

    {
        final Map.Entry<Integer, Turret> e1 = Map.entry(0, new TurretImpl(0, null, 1, 100, 1, 1, 1));
        final Map.Entry<Integer, Turret> e2 = Map.entry(1, new TurretImpl(1, null, 1, 500, 1, 1, 1));
        this.s = new ShopImpl(new TurretsLoader() {
            @Override
            public Map<Integer, Turret> getTurrets() {
                return Map.ofEntries(e1, e2);
            }
        });
    }

    /**
     * Checks if the turret can be bought with the available funds.
     */
    @Test
    void fundsTest() {
        final Player p = new PlayerImpl();
        p.setMoney(INIT_MONEY);
        assertTrue(s.canBuy(0, p));
        assertFalse(s.canBuy(1, p));
        assertFalse(s.canBuy(2, p));
        assertThrows(IllegalArgumentException.class, () -> s.canBuy(0, null));
        assertThrows(IllegalArgumentException.class, () -> s.canBuy(null, p));
    }

    /**
     * Checks if the list of turrets available is correctly set.
     */
    @Test
    void turretListTest() {
        assertFalse(s.getAvailableTurrets().isEmpty());
    }

    /**
     * Checks if the turret is bought correctly.
     */
    @Test
    void buyTest() {
        final Player p = new PlayerImpl();
        final PlayerController pc = new PlayerControllerImpl(p, null);
        final PlayerView pv = new PlayerViewImpl();
        pc.setView(pv);
        pv.setController(pc);
        pv.start();
        p.setMoney(INIT_MONEY);
        final GameManager g = new GameManagerImpl(pc, null);
        final ShopController sc = new ShopControllerImpl(g, s);
        final ShopView sv = new ShopViewImpl();
        sc.setView(sv);
        sv.setController(sc);
        assertTrue(sc.trySetSelectedTurret(this.s.getAvailableTurrets().get(0)));
        assertTrue(sc.buy().isPresent());
        assertEquals(200, p.getMoney());
        assertTrue(sc.trySetSelectedTurret(this.s.getAvailableTurrets().get(0)));
        assertTrue(sc.buy().isPresent());
        assertEquals(100, p.getMoney());
        assertTrue(sc.trySetSelectedTurret(this.s.getAvailableTurrets().get(0)));
        assertTrue(sc.buy().isPresent());
        assertEquals(0, p.getMoney());
        assertFalse(sc.trySetSelectedTurret(this.s.getAvailableTurrets().get(0))); // No funds to buy turret.
        assertTrue(sc.buy().isEmpty());
        assertEquals(0, p.getMoney());
    }
}

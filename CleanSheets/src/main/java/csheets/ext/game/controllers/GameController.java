/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.game.controllers;

import csheets.AppSettings;
import csheets.ext.game.ui.GamePanel;
import csheets.ext.game.ui.TcpService;
import csheets.ext.game.ui.UdpService;
import csheets.ui.ctrl.UIController;

/**
 *
 * @author João Martins
 */
public class GameController {

    /**
     * The UDP Service.
     */
    private UdpService udpService;

    /**
     * The TCP Service.
     */
    private TcpService tcpService;

    SpecificGameController currentGameController;

    boolean startTurn;

    UIController uiController;

    public GameController(UIController uiController) {
        this.uiController = uiController;
    }

    /**
     * Starts the UDP service.
     *
     * @param panel The user interface.
     * @param port The target port that is defined by the user.
     * @param seconds The number of seconds to execute each request.
     */
    public void startUdpService(GamePanel panel, int port, int seconds) {
        if (panel == null) {
            throw new IllegalArgumentException("The user interface cannot be null.");
        }

        this.udpService = new csheets.ext.game.ui.UdpService();

        this.startUdpService(port, seconds);

        this.udpService.addObserver(panel);
    }

    /**
     * Starts the UDP service.
     *
     * @param port The target port that is defined by the user.
     * @param seconds The number of seconds to execute each request.
     */
    private void startUdpService(int port, int seconds) {
        if (port < 0 || port > 49151) {
            throw new IllegalArgumentException("Invalid port was defined. Please select a valid port.");
        }

        if (seconds <= 0) {
            throw new IllegalArgumentException("Invalid seconds number given. It's not possible to register negative or zero seconds.");
        }

        try {
            this.udpService.server(Integer.valueOf(AppSettings.instance().
                    get("TCP_PORT")), Integer.valueOf(AppSettings.instance().
                    get("TCP_PORT")));
            this.udpService.client(seconds);
        } catch (IllegalArgumentException e) {
            this.udpService.stop();

            throw e;
        }
    }

    /**
     * Starts the TCP service.
     *
     * @param panel The user interface.
     * @param port The target port that is defined by the user.
     */
    public void startTcpService(GamePanel panel, int port) {
        if (panel == null) {
            throw new IllegalArgumentException("The user interface cannot be null.");
        }

        this.tcpService = new TcpService(this);

        this.startTcpService(port);

        this.tcpService.addObserver(panel);
    }

    private void startTcpService(int port) {
        if (port < 0 || port > 49151) {
            throw new IllegalArgumentException("Invalid port was defined. Please select a valid port.");
        }

        try {
            this.tcpService.server(port);

        } catch (IllegalArgumentException e) {
            this.tcpService.stop();

            throw e;
        }
    }

    public void establishConnection(String host, String message) {
        this.tcpService.client(host, message);
    }

    public void setContinuousTarget(String target) {
        this.tcpService.setContinuousTarget(target);
    }

    public void stopConnection() {
        this.tcpService.stopContinuousSending();
    }

    public void updateOpponentActiveGames(String target) {
        this.tcpService.updateOpponent(target);
    }

    public void startGame(String selectedValue) {
        if (selectedValue.equalsIgnoreCase("TicTacToe")) {
            currentGameController = new TicTacToeController(uiController,
                    startTurn, tcpService.getTargetIP());
            currentGameController.start();
        }
        if (selectedValue.equals(BattleshipController.GAME_NAME)) {
            currentGameController = new BattleshipController(uiController,
                    startTurn, tcpService.getTargetIP());
            currentGameController.start();
        }
    }

    public void stopCurrentGame() {
        currentGameController.stopGame();
    }

    public void startTurn() {
        startTurn = true;
    }

}

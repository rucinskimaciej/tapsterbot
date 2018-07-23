/*
    MIT License
    Copyright (c) 2018  Pierre-Yves Lapersonne (Twitter: @pylapp, Mail: pylapp(dot)pylapp(at)gmail(dot)com)
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
 */
// ✿✿✿✿ ʕ •ᴥ•ʔ/ ︻デ═一

package pylapp.tapster.client.android.networks

import java.io.IOException


/**
 * Stub based on design pattern Bridge which provides the declarations of methods to call
 * so as to deal with HTTP requests.
 *
 * @author pylapp
 * @since 14/02/2018
 *
 * @version 2.0.0
 */
interface HttpClientStub {


    /* ******************* *
     * METHODS TO OVERRIDE *
     * ******************* */

    /*
     * Some glue
     */

    /**
     * Defines the protocol to use for the server
     *
     * @param protocol - The protocol to use
     */
    fun setServerProtocol(protocol: String)

    /**
     * Defines the port to use for the server
     *
     * @param port - The port to use
     */
    fun setServerPort(port: String)

    /**
     * Defines the IP address to use for the server
     *
     * @param ipAddress - The IP address to use
     */
    fun setServerIpAddress(ipAddress: String)

    /*
     * Robot's API
     */

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "commandTap at (x,y)" command.
     *
     * @param x - The x position of the point to tap on
     * @param y - The y position of the point to tap on
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandTap(x: Int, y: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "n tap" command.
     *
     * @param n - The number of taps to made
     * @param x - The x position of the point to tap on
     * @param y - The y position of the point to tap on
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandTapMany(n: Int, x: Int, y: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "swipe from (startX,startY) to (endX,endY)" command.
     *
     * @param startX - The startX position
     * @param startY - The startY position
     * @param endX - The startY position
     * @param endY - The startY position
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandSwipe(startX: Int, startY: Int, endX: Int, endY: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "swipe from (startX,startY) to (endX,endY), n times" command.
     *
     * @param n - The number of swipes to made
     * @param startX - The startX position
     * @param startY - The startY position
     * @param endX - The startY position
     * @param endY - The startY position
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandSwipeMany(n: Int, startX: Int, startY: Int, endX: Int, endY: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "get 3D position" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandGetPosition(callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "set position to (x,y,z)" command.
     *
     * @param x - The x value
     * @param y - The y
     * @param z - The z value
     * @param callback - A callback to trigger if needed
     * @return [Any]? - Always null
     */
    @Throws(IOException::class)
    fun commandSetPosition(x: Int, y: Int, z: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "reset" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandReset(callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "dance" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandDance(callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "stop dancing" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandStopDance(callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "get angles of arms" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandGetAngles(callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "set angles of arms to (θ1,θ2,θ3)" command.
     *
     * @param θ1 - The x value
     * @param θ2 - The y
     * @param θ3 - The z value
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandSetAngles(θ1: Int, θ2: Int, θ3: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "get status of server" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandGetStatus(callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "get contact point" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandGetContactPoint(callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "draw star" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandDrawStar(callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "draw circle" command.
     *
     * @param x - The X value of the center point
     * @param y - The Y value of the center point
     * @param r - The radius of the circle
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandDrawCircle(x: Int, y: Int, r: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "draw spiral" command.
     *
     * @param x - The X value of the center point
     * @param y - The Y value of the center point
     * @param n - The number of rings of the spiral
     * @param r - The radius of the spiral
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandDrawSpiral(x: Int, y: Int, n: Int, r: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "draw square" command.
     *
     * @param n - The precision of the draw, i.e. draw each n points
     * @param length - The length of the sides of the square
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandDrawSquare(n: Int, length: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "draw cross" command.
     *
     * @param x1 - The X value of the 1st point
     * @param y1 - The Y value of the 1st point
     * @param x2 - The X value of the 2nd point
     * @param y2 - The Y value of the 2nd point
     * @param x3 - The X value of the 3rd point
     * @param y3 - The Y value of the 3rd point
     * @param x4 - The X value of the 4th point
     * @param y4 - The Y value of the 4th point
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandDrawCross(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int, x4: Int, y4: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "draw triangle" command.
     *
     * @param x1 - The X value of the 1st point
     * @param y1 - The Y value of the 1st point
     * @param x2 - The X value of the 2nd point
     * @param y2 - The Y value of the 2nd point
     * @param x3 - The X value of the 3rd point
     * @param y3 - The Y value of the 3rd point
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandDrawTriangle(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int, callback: HttpClientCallback?): Any?

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "draw random pattern" command.
     *
     * @param n - The number of main points to use, to draw n-1 strokes
     * @param minWidth - One of the corner of the area where will be drawn the pattern
     * @param minHeight - One of the corner of the area where will be drawn the pattern
     * @param maxWidth - One of the corner of the area where will be drawn the pattern
     * @param maxHeight - One of the corner of the area where will be drawn the pattern
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    fun commandDrawRandomPattern(n: Int, minWidth: Int, minHeight: Int, maxWidth: Int, maxHeight: Int, callback: HttpClientCallback?): Any?

    /* *************** *
     * INNER INTERFACE *
     * *************** */

    /**
     * A callback to trigger in case of failure or success of a command send through the HTTP client
     */
    interface HttpClientCallback {

        /**
         * Triggered when a command succeeded
         *
         * @param message - The content of the response if existing
         */
        fun onSuccess(message: String?)

        /**
         * Triggered when a command failed
         *
         * @param message - More details about the failure
         */
        fun onFailure(message: String?)

    } // End of interface HttpClientCallback

}
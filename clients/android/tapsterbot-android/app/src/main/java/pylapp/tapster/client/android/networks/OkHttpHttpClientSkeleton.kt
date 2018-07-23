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

import okhttp3.*
import pylapp.tapster.client.android.tools.Config

import java.io.IOException


/**
 * Skeleton based on design pattern Bridge which provides the definitions of methods to call
 * so as to deal with networks and HTTP requests.
 * Based on OkHttp library (http://square.github.io/okhttp/)
 *
 * @author pylapp
 * @since 14/02/2018
 *
 * @version 1.0.0
 */
class OkHttpHttpClientSkeleton : HttpClientStub {


    /* ********** *
     * ATTRIBUTES *
     * ********** */

    /**
     * The protocol of the server to reach
     */
    private var mServerProtocol: String? = ""

    /**
     * The IP address of the server to reach
     */
    private var mServerIpAddress: String? = ""

    /**
     * The port of the server to reach
     */
    private var mServerPort: String? = ""


    /* **************** *
     * COMPANION OBJECT *
     * **************** */

    /**
     * Companion object
     */
    companion object {

        /**
         * The JSON media type
         */
        val MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8")

    }


    /* *************************** *
     * METHODS FROM HttpClientStub *
     * *************************** */

    /**
     * Defines the protocol to use for the server
     *
     * @param protocol - The protocol to use
     */
    override fun setServerProtocol(protocol: String) {
        mServerProtocol = protocol
    }

    /**
     * Defines the port to use for the server
     *
     * @param port - The port to use
     */
    override fun setServerPort(port: String) {
        mServerPort = port
    }

    /**
     * Defines the IP address to use for the server
     *
     * @param ipAddress - The IP address to use
     */
    override fun setServerIpAddress(ipAddress: String) {
        mServerIpAddress = ipAddress
    }


    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "commandTap at (x,y)" command.
     *
     * @param x - The x position of the point to commandTap on
     * @param y - The y position of the point to commandTap on
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    override fun commandTap(x: Int, y: Int, callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_TAP
        val json = "{\"x\": \"$x\", \"y\": \"$y\"}"
        sendPostCommand(url, json, callback)
        return null
    }

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "commandSwipe from (startX,startY) to (endX,endY)" command.
     *
     * @param startX - The startX position
     * @param startY - The startY position
     * @param endX - The startY position
     * @param endY - The startY position
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    override fun commandSwipe(startX: Int, startY: Int, endX: Int, endY: Int,
                              callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_SWIPE
        val json = "{\"startX\": \"$startX\", \"startY\": \"$startY\", \"endX\": \"$endX\", \"endY\": \"$endY\"}"
        sendPostCommand(url, json, callback)
        return null
    }

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "get 3D position" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]? - Always null
     */
    override fun commandGetPosition(callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_GET_POSITION
        sendGetCommand(url, callback)
        return null
    }

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
    override fun commandSetPosition(x: Int, y: Int, z: Int,
                                    callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_SET_POSITION
        val json = "{\"x\": \"$x\", \"y\": \"$y\", \"z\": \"$z\"}"
        sendPostCommand(url, json, callback)
        return null
    }

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "commandReset" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]? - Always null
     */
    override fun commandReset(callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_RESET
        val json = "{}"
        sendPostCommand(url, json, callback)
        return null
    }

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "commandDance" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something is suitable
     */
    override fun commandDance(callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_DANCE
        val json = "{}"
        sendPostCommand(url, json, callback)
        return null
    }

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "stop dancing" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something is suitable
     */
    override fun commandStopDance(callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_STOP_DANCE
        val json = "{}"
        sendPostCommand(url, json, callback)
        return null
    }

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "get angles of arms" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Always null
     */
    override fun commandGetAngles(callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_GET_ANGLES
        sendGetCommand(url, callback)
        return null
    }

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "set angles of arms to (θ1,θ2,θ3)" command.
     *
     * @param θ1 - The x value
     * @param θ2 - The y
     * @param θ3 - The z value
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Always null
     */
    override fun commandSetAngles(θ1: Int, θ2: Int, θ3: Int,
                                  callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_SET_ANGLES
        val json = "{\"theta1\": \"$θ1\", \"theta2\": \"$θ2\", \"theta3\": \"$θ3\"}"
        sendPostCommand(url, json, callback)
        return null
    }

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "get status of server" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Always null
     */
    override fun commandGetStatus(callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_STATUS
        sendGetCommand(url, callback)
        return null
    }

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "get contact point" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Always null
     */
    override fun commandGetContactPoint(callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_CONTACT
        sendGetCommand(url, callback)
        return null
    }

    /**
     * Sends a command through HTTP and returns the result.
     * Here the command is a "draw star" command.
     *
     * @param callback - A callback to trigger if needed
     * @return [Any]?  - Something if suitable
     */
    @Throws(IOException::class)
    override fun commandDrawStar(callback: HttpClientStub.HttpClientCallback?): Any? {
        val url = buildBaseUrl() + Config.ROBOT_URL_PATH_DRAW_STAR
        val json = "{}"
        sendPostCommand(url, json, callback)
        return null
    }


    /* ************* *
     * OTHER METHODS *
     * ************* */

    /**
     * Builds the base URL to reach the robot's server
     *
     * @return [String] - The url with the pattern 'protocol://ip_address:port'
     */
    private fun buildBaseUrl(): String {
        return "$mServerProtocol://$mServerIpAddress:$mServerPort"
    }

    /**
     * Sends a command based on HTTP GET request to this URL and trigger if needed and if defined
     * the callback
     *
     * @param url - The target URL
     * @param callback - The code to trigger in case of success or failure
     */
    private fun sendGetCommand(url: String, callback: HttpClientStub.HttpClientCallback?) {

        // The HTTP client
        val okHttpClient = OkHttpClient()

        // The request
        val request = Request.Builder().url(url).build()

        // Send the asynchronous request
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback?.onFailure(e.message)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                response.body().use({ responseBody ->
                    if (!response.isSuccessful) {
                        callback?.onFailure("Unexpected code: $response")
                        throw IOException("Unexpected code: $response")
                    }
                    callback?.onSuccess(responseBody?.string())
                }) // End of response.body().use
            }
        }) // End of okHttpClient.newCall(request).enqueue

    }

    /**
     * Sends a command base on HTTP POST request to this URL, using this body, and trigger if needed
     * and if defined the callback
     *
     * @param url - The target URL
     * @param payload - The payload to send, in JSON format
     * @param callback - The code to trigger in case of success or failure
     */
    private fun sendPostCommand(url: String, payload: String,
                                callback: HttpClientStub.HttpClientCallback?) {

        // The HTTP client
        val okHttpClient = OkHttpClient()

        // The request with its payload
        val body = RequestBody.create(MEDIA_TYPE_JSON, payload)
        val request = Request.Builder().url(url).post(body).build()

        // Send the asynchronous request
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback?.onFailure(e.message)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                response.body().use({ responseBody ->
                    if (!response.isSuccessful) {
                        callback?.onFailure("Unexpected code: $response")
                        throw IOException("Unexpected code: $response ")
                    }
                    callback?.onSuccess(responseBody?.string())
                }) // End of response.body().use
            }
        }) // End of okHttpClient.newCall(request).enqueue

    }

}
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


package pylapp.tapster.client.android.assistants;

/**
 * A simple class which embeds a message and a status.
 * It can be seen as a bean or a Plain Old Java Object used between the GUI and the assistants
 *
 * @author pylapp
 * @version 1.0.0
 * @since 02/02/2018
 */
public class AssistantMessage {

    /**
     * The type of the message
     */
    public final Type type;

    /**
     * The content of the message
     */
    public final String message;

    /**
     * Simple constructor
     *
     * @param type    - The type of the message
     * @param message - The content of the message
     */
    public AssistantMessage(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    /**
     * The types of messages
     */
    public enum Type {
        /**
         * A debug message
         */
        DEBUG,
        /**
         * A common message
         */
        INFORMATION,
        /**
         * Be talkative!
         */
        VERBOSE,
        /**
         * Just a warning message
         */
        WARNING,
        /**
         * You really must read this message
         */
        ALERT
    } // End of enum Type

}

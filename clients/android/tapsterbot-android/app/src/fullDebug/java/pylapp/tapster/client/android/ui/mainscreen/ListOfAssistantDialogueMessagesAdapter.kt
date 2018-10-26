/*
    MIT License
    Copyright (c) 2018 Pierre-Yves Lapersonne (Mail: dev@pylapersonne.info)
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

package pylapp.tapster.client.android.ui.mainscreen

import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView

import pylapp.tapster.client.android.R
import pylapp.tapster.client.android.assistants.AssistantMessage
import kotlin.collections.ArrayList

/**
 * An adapter for the fragment containing a list of messages
 *
 * @author pylapp
 * @version 2.0.0
 * @since 02/02/2018
 */
class ListOfAssistantDialogueMessagesAdapter : BaseAdapter() {


    /* ********** *
     * ATTRIBUTES *
     * ********** */

    /**
     * The list of messages to display
     */
    /* ************* *
     * OTHER METHODS *
     * ************* */

    /**
     * Returns the list of messages
     *
     * @return ArrayList\<AssistantMessage></AssistantMessage>\>
     */
    val messages: ArrayList<AssistantMessage> = ArrayList()

    /*
     * The log tag
     */
    //private static final String LOG_TAG = ListOfAssistantDialogueMessagesAdapter.class.getSimpleName();


    /* ********************************** *
     * METHODS INHERITED FROM BaseAdapter *
     * ********************************** */

    /**
     * @return int -
     */
    override fun getCount(): Int {
        return messages.size
    }

    /**
     * @param i -
     * @return AssistantMessage -
     */
    override fun getItem(i: Int): AssistantMessage? {
        return messages[i]
    }

    /**
     * @param i -
     * @return long -
     */
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    /**
     * @param position  -
     * @param view      -
     * @param viewGroup -
     * @return View -
     */
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view
        if (view == null) {
            view = View.inflate(viewGroup.context, R.layout.fragment_assistant_dialogue_message, null)
            ViewHolder(view!!)
        }
        val holder = view.tag as ViewHolder
        val item = getItem(position)
                ?: throw IllegalStateException("Null message, is the position parameter correct?")
        holder.tvContent.text = Html.fromHtml(item.message)
        val color: Int = when (item.type) {
            AssistantMessage.Type.INFORMATION -> view.context.resources.getColor(R.color.assistantInformationMessageBackgroundColor)
            AssistantMessage.Type.VERBOSE -> view.context.resources.getColor(R.color.assistantVerboseMessageBackgroundColor)
            AssistantMessage.Type.WARNING -> view.context.resources.getColor(R.color.assistantWarningMessageBackgroundColor)
            AssistantMessage.Type.ALERT -> view.context.resources.getColor(R.color.assistantAlertMessageBackgroundColor)
            AssistantMessage.Type.DEBUG -> view.context.resources.getColor(R.color.assistantDebugMessageBackgroundColor)
        }
        holder.tvContent.setBackgroundColor(color)
        holder.rlParent.setBackgroundColor(color)
        return view
    }


    /* ************* *
     * INNER CLASSES *
     * ************* */

    /**
     * A view holder to use for the items
     */
    private inner class ViewHolder internal constructor(v: View) {

        internal val tvContent: TextView = v.findViewById(R.id.tv_assistant_dialogue_message)
        internal val rlParent: RelativeLayout = v.findViewById(R.id.rl_assistant_dialogue_messages)

        init {
            v.tag = this
        }

    } // End of private class ViewHolder

} // End of public class ListOfUrlsAdapter extends BaseAdapter

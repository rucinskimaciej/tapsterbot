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

package pylapp.tapster.client.android.ui.mainscreen;

import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pylapp.tapster.client.android.R;
import pylapp.tapster.client.android.assistants.AssistantMessage;

/**
 * An adapter for the fragment containing a list of messages
 *
 * @author pylapp
 * @version 1.0.0
 * @since 02/02/2018
 */
public class ListOfAssistantDialogueMessagesAdapter extends BaseAdapter {


    /* ********** *
     * ATTRIBUTES *
     * ********** */

    /**
     * The list of messages to display
     */
    private final List<AssistantMessage> mMessages;

    /*
     * The log tag
     */
    //private static final String LOG_TAG = ListOfAssistantDialogueMessagesAdapter.class.getSimpleName();


    /* *********** *
     * CONSTRUCTOR *
     * *********** */

    /**
     * Default constructor
     */
    public ListOfAssistantDialogueMessagesAdapter() {
        super();
        mMessages = new ArrayList<>();
    }


    /* ********************************** *
     * METHODS INHERITED FROM BaseAdapter *
     * ********************************** */

    /**
     * @return int -
     */
    @Override
    public int getCount() {
        return mMessages.size();
    }

    /**
     * @param i -
     * @return AssistantMessage -
     */
    @Override
    public AssistantMessage getItem(int i) {
        return mMessages.get(i);
    }

    /**
     * @param i -
     * @return long -
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * @param position  -
     * @param view      -
     * @param viewGroup -
     * @return View -
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.fragment_assistant_dialogue_message, null);
            new ViewHolder(view);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        AssistantMessage item = getItem(position);
        if (item == null)
            throw new IllegalStateException("Null message, is the position parameter correct?");
        holder.tvContent.setText(Html.fromHtml(item.message));
        int color;
        switch (item.type) {
            case INFORMATION:
                color = view.getContext().getResources().getColor(R.color.assistantInformationMessageBackgroundColor);
                break;
            case VERBOSE:
                color = view.getContext().getResources().getColor(R.color.assistantVerboseMessageBackgroundColor);
                break;
            case WARNING:
                color = view.getContext().getResources().getColor(R.color.assistantWarningMessageBackgroundColor);
                break;
            case ALERT:
                color = view.getContext().getResources().getColor(R.color.assistantAlertMessageBackgroundColor);
                break;
            case DEBUG:
            default:
                color = view.getContext().getResources().getColor(R.color.assistantDebugMessageBackgroundColor);
                break;
        }
        holder.tvContent.setBackgroundColor(color);
        holder.rlParent.setBackgroundColor(color);
        return view;
    }

    /* ************* *
     * OTHER METHODS *
     * ************* */

    /**
     * Returns the list of messages
     *
     * @return List\<AssistantMessage\>
     */
    public List<AssistantMessage> getMessages() {
        return mMessages;
    }


    /* ************* *
     * INNER CLASSES *
     * ************* */

    /**
     * A view holder to use for the items
     */
    private class ViewHolder {

        final TextView tvContent;
        final RelativeLayout rlParent;

        ViewHolder(View v) {
            tvContent = v.findViewById(R.id.tv_assistant_dialogue_message);
            rlParent = v.findViewById(R.id.rl_assistant_dialogue_messages);
            v.setTag(this);
        }

    } // End of private class ViewHolder

} // End of public class ListOfUrlsAdapter extends BaseAdapter

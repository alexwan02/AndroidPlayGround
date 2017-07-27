

package toast.example.com;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by alexwan on 21/07/2017.
 */

class ShowToastImpl implements IShowToast {

    @Override
    public int showToast(Context context) {
        Toast.makeText(context, "ShowToast Dex", Toast.LENGTH_SHORT).show();
        return 100;
    }
}

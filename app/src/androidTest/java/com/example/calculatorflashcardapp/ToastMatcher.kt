import android.os.IBinder
import android.view.View
import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ToastMatcher : TypeSafeMatcher<Root>() {

    override fun describeTo(description: Description) {
        description.appendText("is toast")
    }

    override fun matchesSafely(root: Root): Boolean {
        val type = root.windowLayoutParams.get().type
        return type == WindowManager.LayoutParams.TYPE_TOAST
    }

    companion object {
        fun isToast(): ToastMatcher {
            return ToastMatcher()
        }
    }
}

package heronsanches.dti.view.databinding.observable

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import heronsanches.dti.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserObservable : BaseObservable(), Parcelable {
    @SerializedName("usuario")
    @Bindable
    var email: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @SerializedName("senha")
    @Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
}
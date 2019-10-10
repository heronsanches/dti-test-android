package heronsanches.dti.model.retrofit.gson

import com.google.gson.annotations.SerializedName

open class DefaultAnswerGson(@SerializedName("sucesso") var success: Boolean = false,
                             @SerializedName("mensagemErro") var errorMessage: String? = null)
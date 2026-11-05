package app.template.patches.lockmeout

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch

@Suppress("unused")
val lockMeOutPremiumPatch = bytecodePatch(
    name = "Unlock Premium",
    description = "Dynamically injects a return-early bypass into the main license gate.",
) {
    execute {
        // Inject the return true assembly sequence directly at index 0
        // to bypass the entire method body natively
        IsPremiumLicenseCheckFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x1
                return v0
            """
        )
    }
}

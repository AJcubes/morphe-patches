package app.template.patches.lockmeout

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch

@Suppress("unused")
val lockMeOutPremiumPatch = bytecodePatch(
    name = "Unlock Premium",
    description = "Dynamically injects a return-early bypass into the main license gate.",
    default = true // ADD THIS LINE: Tells Morphe this patch is enabled by default
) {
    execute {
        IsPremiumLicenseCheckFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x1
                return v0
            """
        )
    }
}

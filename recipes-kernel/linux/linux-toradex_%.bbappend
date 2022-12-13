FILESEXTRAPATHS_prepend := "${THISDIR}/linux-toradex:"

CUSTOM_DRIVER = "panel-ampire-am4001280atzqw00h"
CUSTOM_DEVICETREE = "verdin-imx8mm_am4001280atzqw00h.dts"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

# Fetch the driver and overlay from a remote source, read the Makefile/Kconf patch which adds the driver and apply it with a kernel config fragment.
SRC_URI += " \
    git://github.com/j-gre/display-image-firmware.git;branch=main;protocol=https;destsuffix=./git-firmware \
    file://am4001280atzw00h.patch \
    file://am4001280atzw00h.cfg \
"

# Copy the device tree and driver to the internal kernel sources (in order to build them in-tree).
do_configure_append() {

    # Panel driver copy operation
    cp --no-preserve=ownership ${WORKDIR}/git-firmware/drivers/display/${CUSTOM_DRIVER}/${CUSTOM_DRIVER}.c ${S}/drivers/gpu/drm/panel

    # Devicetree copy operation
    # For arm32 bit devices
    # cp --no-preserve=ownership ${WORKDIR}/git-firmware/overlays/toradex/${CUSTOM_DEVICETREE} ${S}/arch/arm/boot/dts
    # For arm64 bit freescale/NXP devices
    cp --no-preserve=ownership ${WORKDIR}/git-firmware/overlays/toradex/${CUSTOM_DEVICETREE} ${S}/arch/arm64/boot/dts/freescale
}
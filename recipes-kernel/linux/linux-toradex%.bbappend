FILESEXTRAPATHS_prepend := "${THISDIR}/linux-toradex:"

# Prevent the use of in-tree defconfig
unset KBUILD_DEFCONFIG

CUSTOM_DEVICETREE = "verdin-imx8mm_am4001280_overlay.dts"

SRC_URI += "\ 
    file://${CUSTOM_DEVICETREE} \
    "

do_configure_append() {
    # For arm32 bit devices
    #cp ${WORKDIR}/${CUSTOM_DEVICETREE} ${S}/arch/arm/boot/dts
    # For arm64 bit freescale/NXP devices
    cp ${WORKDIR}/${CUSTOM_DEVICETREE} ${S}/arch/arm64/boot/dts/freescale
}

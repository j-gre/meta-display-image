FILESEXTRAPATHS_prepend := "${THISDIR}/linux-toradex:"

CUSTOM_DEVICETREE = "verdin-imx8mm_am4001280_overlay.dts"

SRC_URI += "\ 
    file://${CUSTOM_DEVICETREE} \
    "

do_configure_append() {
    # For arm32 bit devices
    # cp ${WORKDIR}/${CUSTOM_DEVICETREE} ${S}/arch/arm/boot/dts
    # For arm64 bit freescale/NXP devices
    cp ${WORKDIR}/${CUSTOM_DEVICETREE} ${S}/arch/arm64/boot/dts/freescale
}

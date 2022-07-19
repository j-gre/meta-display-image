do_configure_append() {
    sed -i 's/#define FDT_FILE.*/#define FDT_FILE "verdin-imx8mm_am4001280_overlay.dtb"/' ${S}/include/configs/verdin-imx8mm.h
}

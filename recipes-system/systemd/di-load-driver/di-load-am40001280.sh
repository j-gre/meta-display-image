#!/bin/bash
# Test: Manually load 32e10000.mipi_dsi
sh -c "echo 32e10000.mipi_dsi > /sys/bus/platform/drivers/imx_sec_dsim_drv/bind"
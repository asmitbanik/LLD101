public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        ProjectorDevice pj = reg.getFirstOfType(ProjectorDevice.class);
        pj.powerOn();
        pj.connectInput("HDMI-1");

        LightsDevice lights = reg.getFirstOfType(LightsDevice.class);
        lights.setBrightness(60);

        AcDevice ac = reg.getFirstOfType(AcDevice.class);
        ac.setTemperatureC(24);

        ScannerDevice scan = reg.getFirstOfType(ScannerDevice.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirstOfType(ProjectorDevice.class).powerOff();
        reg.getFirstOfType(LightsDevice.class).powerOff();
        reg.getFirstOfType(AcDevice.class).powerOff();
    }
}

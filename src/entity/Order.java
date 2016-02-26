package entity;

/**
 * ����� ��� �������� ������ ������
 */
public class Order {

    // ������������� ������
    private Long orderId;
    // ������
    private Long custumerId;
    // ���� ����� "2015-02-18T00:00:00"
    private String dateTime;
    // ������ (���, ����, ��������)
    private byte orderStatus;
    // ������ ������
    private String FromString;
    // ������ �������������� ���������� ������� (���� 56.9613438,24.1900393)
    private String FromLocation;
    // ���� �����
    private String ToString;
    // ���� �������������� ���������� ������� (���� 56.9613438,24.1900393)
    private String ToLocation;
    // �����
    private Long taxiId;
    // ���������
    private double distance;
    // ���������
    private double price;
    // rating  [1..10]
    private byte rate;
    // �����
    private String feedback;

}

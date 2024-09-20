package com.GestionPresence.Presence.repository;

import com.GestionPresence.Presence.config.DatabaseConnection;
import com.GestionPresence.Presence.entity.AbsenceCountByCourse;
import com.GestionPresence.Presence.entity.Presence;
import com.GestionPresence.Presence.entity.PresenceStatus;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PresenceDAO {

    private static final String INSERT_PRESENCE_QUERY = "INSERT INTO presence (student_id, course_id, presence_date, status) VALUES (?, ?, ?, ?)";
    private static final String SELECT_PRESENCE_BY_ID_QUERY = "SELECT * FROM presence WHERE presence_id = ?";
    private static final String SELECT_ALL_PRESENCES_QUERY = "SELECT * FROM presence";
    private static final String UPDATE_PRESENCE_QUERY = "UPDATE presence SET student_id = ?, course_id = ?, presence_date = ?, status = ? WHERE presence_id = ?";
    private static final String DELETE_PRESENCE_QUERY = "DELETE FROM presence WHERE presence_id = ?";
    private static final String SELECT_ALL_PRESENCES_BY_STUDENT_QUERY = "SELECT * FROM presence WHERE student_id = ?";
    private static final String UPDATE_NOTIFICATION_MESSAGE_QUERY = "UPDATE student SET cor_notification = ? WHERE student_id = ?";
    private static final String COUNT_UNJUSTIFIED_ABSENCES_QUERY = "SELECT COUNT(*) FROM presence WHERE student_id = ? AND status = 'ABSENT'";
    private static final String SELECT_ABSENCES_COUNT_BY_COURSE_QUERY = "SELECT course_id, COUNT(*) as absence_count FROM presence WHERE student_id = ? AND status = 'ABSENT' GROUP BY course_id";

    public void addPresence(Presence presence) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PRESENCE_QUERY)) {
            // Ajout de la nouvelle présence
            statement.setString(1, presence.getStudentId());
            statement.setInt(2, presence.getCourseId());
            statement.setDate(3, new java.sql.Date(presence.getPresenceDate().getTime()));
            statement.setString(4, presence.getStatus().name());
            statement.executeUpdate();

            // Après l'ajout de la présence, on vérifie si l'étudiant a atteint 3 absences injustifiées
            if (PresenceStatus.ABSENT.equals(presence.getStatus())) {
                int unjustifiedAbsences = countUnjustifiedAbsences(presence.getStudentId());

                // Si le nombre d'absences injustifiées est supérieur ou égal à 3, ajouter une notification
                if (unjustifiedAbsences >= 3) {
                    String message = "Vous avez " + unjustifiedAbsences + " absences injustifiées. Veuillez contacter l'administration.";
                    updateNotificationMessage(presence.getStudentId(), message);
                }
            }
        }
    }


    public Presence getPresence(int presenceId) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRESENCE_BY_ID_QUERY)) {
            statement.setInt(1, presenceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToPresence(resultSet);
                }
            }
        }
        return null;
    }

    public List<Presence> getAllPresences() throws SQLException {
        List<Presence> presences = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_PRESENCES_QUERY)) {
            while (resultSet.next()) {
                presences.add(mapResultSetToPresence(resultSet));
            }
        }
        return presences;
    }

    public boolean updatePresence(Presence presence) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRESENCE_QUERY)) {
            statement.setString(1, presence.getStudentId());
            statement.setInt(2, presence.getCourseId());
            statement.setDate(3, new java.sql.Date(presence.getPresenceDate().getTime()));
            statement.setString(4, presence.getStatus().name());
            statement.setInt(5, presence.getPresenceId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public boolean deletePresence(int presenceId) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRESENCE_QUERY)) {
            statement.setInt(1, presenceId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    public List<Presence> getAllPresencesByStudent(String studentId) throws SQLException {
        List<Presence> presences = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRESENCES_BY_STUDENT_QUERY)) {
            statement.setString(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    presences.add(mapResultSetToPresence(resultSet));
                }
            }
        }
        return presences;
    }

    public void updateNotificationMessage(String studentId, String message) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_NOTIFICATION_MESSAGE_QUERY)) {
            statement.setString(1, message);
            statement.setString(2, studentId);
            statement.executeUpdate();
        }
    }


    public int countUnjustifiedAbsences(String studentId) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_UNJUSTIFIED_ABSENCES_QUERY)) {
            statement.setString(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        return 0;
    }


    public List<AbsenceCountByCourse> getAbsencesCountByCourse(String studentId) throws SQLException {
        List<AbsenceCountByCourse> absencesCount = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ABSENCES_COUNT_BY_COURSE_QUERY)) {
            statement.setString(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    AbsenceCountByCourse countByCourse = new AbsenceCountByCourse();
                    countByCourse.setCourseId(resultSet.getInt("course_id"));
                    countByCourse.setAbsenceCount(resultSet.getInt("absence_count"));
                    absencesCount.add(countByCourse);
                }
            }
        }
        return absencesCount;
    }

    private Presence mapResultSetToPresence(ResultSet resultSet) throws SQLException {
        Presence presence = new Presence();
        presence.setPresenceId(resultSet.getInt("presence_id"));
        presence.setStudentId(resultSet.getString("student_id"));
        presence.setCourseId(resultSet.getInt("course_id"));
        presence.setPresenceDate(resultSet.getDate("presence_date"));
        presence.setStatus(PresenceStatus.valueOf(resultSet.getString("status")));
        return presence;
    }
}

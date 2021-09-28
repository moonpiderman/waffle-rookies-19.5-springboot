package com.wafflestudio.seminar.domain.user.model

import com.wafflestudio.seminar.domain.model.BaseTimeEntity
import com.wafflestudio.seminar.domain.seminar.model.Seminar
import com.wafflestudio.seminar.domain.seminar.model.SeminarParticipant
import net.bytebuddy.implementation.bind.annotation.Default
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "participant")
class ParticipantProfile (
    @Column
    var university: String? = "",

    @field:NotNull
    var accepted: Boolean? = true,

    // user_id에 관한 필드가 존재해야한다.
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User? = null,

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "participant_profile_id")
    val seminarParticipant : MutableList<SeminarParticipant> = mutableListOf()
) : BaseTimeEntity() {
    fun enrollSeminar(mappingSeminar: SeminarParticipant) {
        seminarParticipant.add(mappingSeminar)
    }
}
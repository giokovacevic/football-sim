import "./pitch_style.css";
import PitchMember from "./PitchMember";
function Pitch({editable, club, kits, dragData, setDragData, substitute}:any) {

    return (
        <div className="pitch">
            {club.roster.lineup.formationDTO.requiredRoleDTOs.map((role:any, index:number) => {
                const starter = club.roster.lineup.starters[role.stringValue];
                
                if(editable) {
                    return (
                        <PitchMember key={index} editable={true} _role={role} starter={starter} clubId={club.id} kits={kits} setDragData={setDragData} dragData={dragData} substitute={substitute}></PitchMember>
                    );
                }else{
                    return (
                        <div key={index}>Not editable</div>
                    );
                }
                
            })}

        </div>
    );
}
export default Pitch;